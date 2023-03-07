package com.hmshohrab.communityfinance.ui.discount

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hmshohrab.communityfinance.R
import com.hmshohrab.communityfinance.core.BaseFragment
import com.hmshohrab.communityfinance.core.SimpleCallback
import com.hmshohrab.communityfinance.core.Status
import com.hmshohrab.communityfinance.data.model.DiscountPartner
import com.hmshohrab.communityfinance.databinding.FragmentDiscountPartnerBinding
import com.hmshohrab.communityfinance.ui.MainVM
import com.hmshohrab.communityfinance.ui.discount.adapter.DiscountPartnerAdapter
import com.hmshohrab.communityfinance.utils.hide
import com.hmshohrab.communityfinance.utils.show

/**
 * A simple [Fragment] subclass.
 * Use the [DiscountPartnerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscountPartnerFragment : BaseFragment<MainVM>() {

    override val viewModel: MainVM by viewModels()
    private lateinit var binding: FragmentDiscountPartnerBinding
    private var pcId = ""
    private var title = ""
    private var discountPartnerAdapter: DiscountPartnerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pcId = requireArguments().getString("pcId", "")
        title = requireArguments().getString("title", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscountPartnerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun setUpView() {
        binding.toolbar.title = title.ifEmpty { "Category" }
        binding.rv.apply {
            discountPartnerAdapter = DiscountPartnerAdapter()
            discountPartnerAdapter?.listener = object : SimpleCallback<DiscountPartner> {
                override fun callback(any: DiscountPartner) {
                    val bundle = bundleOf()
                    bundle.putString("data", Gson().toJson(any))
                    findNavController().navigate(
                        R.id.action_discountPartnerFragment_to_discountPartnerDetailsFragment,
                        bundle
                    )
                }
            }
            layoutManager = LinearLayoutManager(requireContext())
            adapter = discountPartnerAdapter
        }
        viewModel.getDiscountPartner(pcId)
    }

    override fun observeViewModelEvents() {
        viewModel.partnersLiveData.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.show()
                }
                Status.SUCCESS -> {
                    discountPartnerAdapter?.differ?.submitList(it.data)
                    Log.d("partnersLiveData: ", Gson().toJson(it.data))
                    binding.progressBar.hide()

                }
                Status.ERROR -> {
                    binding.progressBar.hide()
                }
            }
        }

    }
}