package com.artistecomm.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.artistecomm.ViewModel.UserViewModel
import com.artistecomm.model.User
import com.artistecomm.Interface.updateInterface

import com.artistecomm.roomdb_test.R
import kotlinx.android.synthetic.main.fragment_list.view.*

class listFragment : Fragment(), updateInterface {

    lateinit var mUserViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_list, container, false)
        val adapter = ListAdapter(requireContext(),this)
        val recyclerView = view.userDataRecyclerView
        val textView = view.textviewtitle


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user->
            adapter.setData(user)})

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        return view
    }

    override fun getUserValue(user: User) {
        findNavController().navigate(R.id.action_listFragment_to_updateFragment)
    }
}