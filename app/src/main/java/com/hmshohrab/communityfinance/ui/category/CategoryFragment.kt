package com.hmshohrab.communityfinance.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hmshohrab.communityfinance.R
import com.hmshohrab.communityfinance.core.BaseFragment
import com.hmshohrab.communityfinance.core.SimpleCallback
import com.hmshohrab.communityfinance.core.Status
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.databinding.FragmentCategoryBinding
import com.hmshohrab.communityfinance.ui.MainVM
import com.hmshohrab.communityfinance.ui.category.adapter.CategoriesAdapter
import com.hmshohrab.communityfinance.utils.hide
import com.hmshohrab.communityfinance.utils.show


/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : BaseFragment<MainVM>() {

    override val viewModel: MainVM by activityViewModels()
    private lateinit var binding: FragmentCategoryBinding
    private var pcId = ""
    private var title = ""
    private var categoriesAdapter: CategoriesAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pcId = requireArguments().getString("pcId","")
        title = requireArguments().getString("title","")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        binding.toolbar.title = title.ifEmpty { "Category" }

            binding.rv.apply {
            categoriesAdapter = CategoriesAdapter()
            categoriesAdapter?.listener = object : SimpleCallback<Category> {
                override fun callback(any: Category) {
                    val bundle = bundleOf()
                    bundle.putString("pcId", any.pc_id)
                    bundle.putString("title", any.name)
                    if (any.child_count > 0) {
                            findNavController().navigate(R.id.categoryFragment, bundle)
                    }else if(any.child_count == 0){
                        findNavController().navigate(R.id.action_categoryFragment_to_discountPartnerFragment, bundle)
                    }

                }
            }
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoriesAdapter
        }
        viewModel.getCategory(pcId)

    }

    override fun observeViewModelEvents() {
        viewModel.categoryListLiveData.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.show()
                }
                Status.SUCCESS -> {
                    categoriesAdapter?.differ?.submitList(it.data)
                        Log.d("data",Gson().toJson(it.data))
                    binding.progressBar.hide()

                }
                Status.ERROR -> {
                    binding.progressBar.hide()
                }
            }
        }
    }

}