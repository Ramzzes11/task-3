package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.model.Contact
import com.example.myapplication.model.getInCircleGlide
import kotlinx.android.synthetic.main.my_contact.view.*



class ContactAdapter(val listener: Listener) :RecyclerView.Adapter<ContactAdapter.ContactHolder>() {


    private var contactList = emptyList<Contact>()
        set(value) {
            val diff = ContactDiffUtill(field,value)
            val difResult = DiffUtil.calculateDiff(diff)
            field = value
            difResult.dispatchUpdatesTo(this)
//            notifyDataSetChanged()
        }

    class ContactHolder(item: View): ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.my_contact,parent,
            false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        with(holder.itemView) {
            btDelete.setOnClickListener{
                listener.deleteByPushOnBasket(position)
            }
            CardView.setOnClickListener{
                //val extras = FragmentNavigatorExtras(contactImage to "big_photo")
                listener.openContactProfile(position,contactList[position].nameSurname,contactList[position].career,
                        contactList[position].photo)
            }
            //getInCircleCoil(contactList[position].photo,contactImage)
            getInCircleGlide(contactList[position].photo,contactImage)
            name_surname.text = contactList[position].nameSurname
            career.text = contactList[position].career
        }
    }

    interface Listener{
        fun deleteByPushOnBasket(position: Int)
        fun openContactProfile(
            position: Int,
            nameSurname: String,
            career: String,
            inCircleGlide: String
        )
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setList(list: List<Contact>){
        contactList = list
    }
}

class ContactDiffUtill( private val oldList: List<Contact>,
                        private val newList: List<Contact>
                        ):DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newList[newItemPosition]
        val old = oldList[oldItemPosition]
        return old.id==new.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newList[newItemPosition]
        val old = oldList[oldItemPosition]
        return new==old
    }

}