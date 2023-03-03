package com.hmshohrab.communityfinance.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hmshohrab.communityfinance.R
import com.hmshohrab.communityfinance.core.BaseFragment
import com.hmshohrab.communityfinance.data.model.LabelValueItem
import com.hmshohrab.communityfinance.databinding.FragmentPaymentDetailsBinding
import com.hmshohrab.communityfinance.manager.DialogManager
import com.hmshohrab.communityfinance.manager.DialogManager.openReceiptDialog
import com.hmshohrab.communityfinance.ui.MainVM
import com.hmshohrab.communityfinance.utils.PDFConverter
import com.hmshohrab.communityfinance.utils.Utils
import com.hmshohrab.communityfinance.utils.hide
import com.hmshohrab.communityfinance.utils.show
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [PaymentDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentDetailsFragment : BaseFragment<MainVM>() {
    override val viewModel: MainVM by activityViewModels()
    private lateinit var binding: FragmentPaymentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPaymentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        val args = requireArguments()
        val title = args.getString("title", "")
        val icon = args.getInt("payIcon", R.drawable.ic_nagad)
        binding.apply {
            progressBar.hide()
            layoutPaymentName.hint = "$title Name"
            layoutPaymentNumber.hint = "$title Number"
            layoutPaymentNarration.hint = "Narration"
            layoutPaymentAmount.hint = "Amount"
            val labelValueList = mutableListOf<LabelValueItem>()

            btnSubmit.setOnClickListener {
                if (validation()) {
                    labelValueList.clear()
                    labelValueList.add(
                        LabelValueItem(
                            label = "$title Name",
                            value = inputPaymentName.text.toString()
                        )
                    )
                    labelValueList.add(
                        LabelValueItem(
                            label = "$title Number",
                            value = inputPaymentNumber.text.toString()
                        )
                    )
                    labelValueList.add(
                        LabelValueItem(
                            label = "Narration",
                            value = inputPaymentNarration.text.toString()
                        )
                    )
                    labelValueList.add(
                        LabelValueItem(
                            label = "Amount",
                            value = inputPaymentAmount.text.toString()
                        )
                    )
                    labelValueList.add(LabelValueItem("Date & Time", Utils.getLocalTime()))

                    viewModel.address?.getAddressLine(0)
                        ?.let { it1 -> LabelValueItem(label = "Location", value = it1) }
                        ?.let { it2 -> labelValueList.add(it2) }
                    val hashMap = hashMapOf<String, String>()
                    hashMap["name"] = inputPaymentName.text.toString()
                    hashMap["number"] = inputPaymentNumber.text.toString()
                    hashMap["amount"] = inputPaymentAmount.text.toString()
                    hashMap["narration"] = inputPaymentNarration.text.toString()
                    hashMap["payIcon"] = icon.toString()
                    hashMap["location"] = viewModel.address?.getAddressLine(0).toString()
                    hashMap["time"] = Utils.getLocalTime()

                    DialogManager.openConfirmDialog(requireContext(), labelValueList) {
                        lifecycleScope.launch {
                            viewModel.loading.postValue(true)
                            delay(2000)
                            viewModel.loading.postValue(false)
                            openReceiptDialog(requireContext(), hashMap) {
                                //val bitmap = Utils.viewToImage(it)
                                PDFConverter().createPdf(requireContext(), it, "CIBL")
                                findNavController().popBackStack()
                            }
                        }

                    }
                }
            }
        }
    }

    override fun observeViewModelEvents() {
        viewModel.loading.observe(this) {
            if (it) binding.progressBar.show() else binding.progressBar.hide()
        }
    }

    private fun validation(): Boolean {
        if (binding.inputPaymentName.text.toString().isEmpty()) {
            binding.layoutPaymentName.error = "Please enter your Name"
            binding.inputPaymentName.requestFocus()
            return false
        }
        if (binding.inputPaymentNumber.text.toString().isEmpty()) {
            binding.layoutPaymentNumber.error = "Please enter your Number"
            binding.inputPaymentNumber.requestFocus()
            return false
        }
        if (binding.inputPaymentAmount.text.toString().isEmpty()) {
            binding.layoutPaymentAmount.error = "Please enter your Amount"
            binding.inputPaymentAmount.requestFocus()
            return false
        }
        if (binding.inputPaymentNarration.text.toString().isEmpty()) {
            binding.layoutPaymentNarration.error = "Please enter your Narration"
            binding.inputPaymentNarration.requestFocus()
            return false
        }
        return true
    }


}