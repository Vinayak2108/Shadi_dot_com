package com.shaadi.codechallenge.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.codechallenge.R

import com.shaadi.codechallenge.adapters.UserListAdapter
import com.shaadi.codechallenge.databinding.UserListFragmentBinding
import com.shaadi.codechallenge.model.localdb.ACCEPTED
import com.shaadi.codechallenge.model.localdb.REJECTED
import com.shaadi.codechallenge.model.localdb.User
import com.shaadi.codechallenge.repository.DataRepository
import com.shaadi.codechallenge.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserListFragment : Fragment(), OnItemClickListener{

    private lateinit var viewModel: UserListViewModel
    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModel.loadData()
        viewModel.userList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.userList.adapter = UserListAdapter(it,this)
            }
        })
        viewModel.notifyChanges.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.userList.adapter?.notifyDataSetChanged()
                viewModel.resetNotify()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(viewId: Int, dataItem: User) {
        when(viewId){
            R.id.acceptButton->{
                dataItem.acceptanceStatus = ACCEPTED
                viewModel.updateUser(dataItem)
            }
            R.id.rejectButton->{
                dataItem.acceptanceStatus = REJECTED
                viewModel.updateUser(dataItem)
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(viewId: Int, dataItem: User)
}
