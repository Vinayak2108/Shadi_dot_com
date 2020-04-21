package com.shaadi.codechallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.TextUtilsCompat
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.codechallenge.R
import com.shaadi.codechallenge.databinding.UserListItemBinding
import com.shaadi.codechallenge.model.localdb.ACCEPTED
import com.shaadi.codechallenge.model.localdb.NOT_SET
import com.shaadi.codechallenge.model.localdb.REJECTED
import com.shaadi.codechallenge.model.localdb.User
import com.shaadi.codechallenge.repository.DataRepository
import com.shaadi.codechallenge.view.fragments.OnItemClickListener
import com.shaadi.codechallenge.view.fragments.UserListFragment

class UserListAdapter(private val data: ArrayList<User>?,val callBack: OnItemClickListener) :RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val dataItem = data!![position]
            val binding = holder.binding
            val context  = binding.root.context
            binding.name.text = context.getString(R.string.name,dataItem.name.title,dataItem.name.first,dataItem.name.last)
            binding.ageAndGender.text = context.getString(R.string.age_and_gender,dataItem.dob.age,dataItem.gender.capitalize())
            val contactNo = when{
                !dataItem.cell.isNullOrBlank() -> dataItem.cell
                !dataItem.phone.isNullOrBlank() -> dataItem.phone
                else -> context.getString(R.string.no_contact_shared)
            }
            binding.contact.text = contactNo
            binding.city.text = dataItem.location.city
            binding.stateAndCountry.text = context.getString(R.string.state_and_country,dataItem.location.state,dataItem.location.country)
            binding.profileImage.setImageURI(dataItem.picture.large)

            if(dataItem.acceptanceStatus == NOT_SET){
                binding.controls.visibility = View.VISIBLE
                binding.acceptanceStatus.visibility = View.GONE
            }else{
                binding.controls.visibility = View.GONE
                binding.acceptanceStatus.visibility = View.VISIBLE
                when(dataItem.acceptanceStatus){
                    ACCEPTED->{binding.acceptanceStatus.text = context.getString(R.string.accepted)}
                    REJECTED->{binding.acceptanceStatus.text = context.getString(R.string.rejected)}
                }
            }

            binding.acceptButton.setOnClickListener {
                callBack.onItemClick(it.id,dataItem)

            }
        binding.rejectButton.setOnClickListener {
            callBack.onItemClick(it.id,dataItem)
        }


    }
}



class UserViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {}