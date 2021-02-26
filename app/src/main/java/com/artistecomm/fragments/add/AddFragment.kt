package com.artistecomm.fragments.add

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.artistecomm.ViewModel.UserViewModel
import com.artistecomm.data.User
import com.artistecomm.roomdb_test.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val view= inflater.inflate(R.layout.fragment_add, container, false)
        view.ADDbutton.setOnClickListener{
            insertDataIntoDataBase()
        }
        return view
    }

    private fun insertDataIntoDataBase()
    {
        val firstname = editTextFirstName.text.toString()
        val lastname = editTextLastName.text.toString()
        val age = editTextAge.text.toString()
        if(checkInput(firstname,lastname,age))
        {
            val user = User(0,firstname ,lastname ,age.toInt())
            mUserViewModel.addUserToDb(user)
            Toast.makeText(requireContext() ,"Successfull", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

    fun checkInput(fname:String , lname:String, age:String):Boolean {
        if (!fname.isEmpty() && !lname.isEmpty() && !age.isEmpty())
            return true
        else
           return false

    }


}
