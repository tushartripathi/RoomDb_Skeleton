package com.artistecomm.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.artistecomm.ViewModel.UserViewModel
import com.artistecomm.model.User
import com.artistecomm.roomdb_test.R
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        var view =  inflater.inflate(R.layout.fragment_update, container, false)
        view.updateTextFirstName.setText(args.userVal.firstName)
        view.updateTextLastName.setText(args.userVal.lastName)
        view.updateTextAge.setText(args.userVal.age.toString())

        view.updatebutton.setOnClickListener{
            updateValues();
        }
        return view
    }

    fun updateValues(){
        val firstName = updateTextFirstName.text.toString()
        val lastname = updateTextLastName.text.toString()
        val age = updateTextAge.text

            if(inputCheck(firstName,lastname,age))
            {
                val user = User(args.userVal.id,firstName,lastname,Integer.parseInt(age.toString()))
                mUserViewModel.updateUser(user)
                Toast.makeText(requireContext(),"Successfully Updated",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }


    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}