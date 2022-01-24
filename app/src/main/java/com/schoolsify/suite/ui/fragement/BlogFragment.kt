package com.schoolsify.suite.ui.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.schoolsify.suite.HomeViewModel
import com.schoolsify.suite.R
import com.schoolsify.suite.data.network.Userpreference
import com.schoolsify.suite.databinding.FragmentBlogBinding
import com.schoolsify.suite.databinding.FragmentContactBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class BlogFragment : Fragment() {
    private var _binding: FragmentBlogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()


    @Inject
    lateinit var userpreference: Userpreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}