package com.example.miniprojecttest.view.common

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.miniprojecttest.domain.model.Failure
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : DaggerFragment() {

    var viewBinding: VB? = null
    protected var viewModel: VM? = null

    private var navController: NavController? = null

    private fun initNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getUiBinding()
        return viewBinding?.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(initMenu(), menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = bindViewModel()
        super.onViewCreated(view, savedInstanceState)
        onFirstLaunch(savedInstanceState, view)
        initNavController(view)
        initUiListener()
        observeViewModel(viewModel!!)
    }

    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(savedInstanceState: Bundle?, view: View)
    abstract fun initUiListener()
    abstract fun initMenu(): Int
    abstract fun observeViewModel(viewModel: VM)
    abstract fun bindViewModel(): VM

    fun getParentFm() = requireActivity().supportFragmentManager

    fun getChildFm() = childFragmentManager

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }

    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showToast("Koneksi error, mohon periksa kembali jaringan anda")
            is Failure.ServerError -> showToast(failure.message)
            else -> showToast("Terjadi kesalahan, mohon coba kembali")
        }
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun getController() = navController

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        viewModel = null
    }
}