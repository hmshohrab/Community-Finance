package com.hmshohrab.communityfinance.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hmshohrab.communityfinance.R
import com.hmshohrab.communityfinance.core.BaseFragment
import com.hmshohrab.communityfinance.data.model.Item
import com.hmshohrab.communityfinance.databinding.FragmentDashboardBinding
import com.hmshohrab.communityfinance.ui.MainVM
import com.hmshohrab.communityfinance.ui.dashboard.adapter.PaymentListAdapter
import com.hmshohrab.communityfinance.core.SimpleCallback

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : BaseFragment<MainVM>() {
    override val viewModel: MainVM by activityViewModels()
    private lateinit var binding: FragmentDashboardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        val itemList: MutableList<Item> = mutableListOf()
        itemList.add(Item("Bkash", R.drawable.bkash_money_send_icon))
        itemList.add(Item("Nagad", R.drawable.ic_nagad))
        //Initialize Recyclerview
        binding.rv.apply {
            val paymentListAdapter = PaymentListAdapter()
            paymentListAdapter.listener = object : SimpleCallback<Item> {
                override fun callback(any: Item) {
                    val bundle = bundleOf()
                    bundle.putString("title", any.title)
                    bundle.putInt("payIcon",any.icon)
                    findNavController().navigate(R.id.paymentDetailsFragment, bundle)
                }
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = paymentListAdapter
            paymentListAdapter.differ.submitList(itemList)
        }
    }
}

