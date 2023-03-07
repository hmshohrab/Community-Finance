package com.hmshohrab.communityfinance.ui.discount

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.hmshohrab.communityfinance.core.BaseFragment
import com.hmshohrab.communityfinance.data.model.DiscountPartner
import com.hmshohrab.communityfinance.databinding.FragmentDiscountPartnerDetailsBinding
import com.hmshohrab.communityfinance.ui.MainVM
import com.hmshohrab.communityfinance.ui.discount.adapter.DiscountPartnerAdapter
import com.hmshohrab.communityfinance.utils.hide

/**
 * A simple [Fragment] subclass.
 * Use the [DiscountPartnerDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscountPartnerDetailsFragment : BaseFragment<MainVM>() {

    override val viewModel: MainVM by viewModels()
    private lateinit var binding: FragmentDiscountPartnerDetailsBinding
    private var data: DiscountPartner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = requireArguments().getString("data", "")
        data = Gson().fromJson(model, DiscountPartner::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscountPartnerDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun setUpView() {
        binding.toolbar.title = data?.partner_name

            binding.apply {
            progressBar.hide()
            txtDesc.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(data?.details, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(data?.details)
            }
            txtDiscount.text = if(data?.discountPercentage.isNullOrEmpty()) "" else "${data?.discountPercentage}%"
            txtAddress.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(data?.address, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(data?.address)
            }

        }

    }

}