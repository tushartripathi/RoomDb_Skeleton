package com.artistecomm.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.artistecomm.model.User
import com.artistecomm.Interface.deleteUserInterface
import com.artistecomm.roomdb_test.R
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter(context : Context,inter: deleteUserInterface) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    lateinit var mcontext:Context
    lateinit var deleteUser : deleteUserInterface
    var userList = emptyList<User>()

    init {
        mcontext= context
        deleteUser = inter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPost = userList.get(position)
        holder.itemView.idtextView.text = currentPost.id.toString()
        holder.itemView.firstNametextView.text = currentPost.firstName.toString()
        holder.itemView.lastnametextView.text = currentPost.lastName.toString()
        holder.itemView.agetextView.text = currentPost.age.toString()

        holder.itemView.rowLaoyoutID.setOnClickListener {
           val action = listFragmentDirections.actionListFragmentToUpdateFragment(currentPost)
            holder.itemView.findNavController().navigate(action) }

        holder.itemView.deleteBtn.setOnClickListener {
                deleteUser.getUserValue(currentPost)
        }
    }

    fun setData(user : List<User>)
    {
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }



}