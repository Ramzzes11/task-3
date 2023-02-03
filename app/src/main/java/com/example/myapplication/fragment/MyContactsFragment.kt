package com.example.myapplication.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.ContactAdapter
import com.example.myapplication.adapter.SwipeToDeleteCallback
import com.example.myapplication.databinding.FragmentMyContactsBinding
import com.example.myapplication.model.Contact
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.dialog_add_contact.*
import kotlin.random.Random

class MyContactsFragment : Fragment(), ContactAdapter.Listener {

    lateinit var bindingContact: FragmentMyContactsBinding
    lateinit var adapter: ContactAdapter
    lateinit var contactList: MutableList<Contact>
    lateinit var list: MutableList<Contact>
    lateinit var dialog: Dialog
    lateinit var recyclerView: RecyclerView
    lateinit var returnContact: Contact


    private val contactInfo = listOf(
        "https://klike.net/uploads/posts/2019-12/1576662752_30.jpg",
        "https://i.pinimg.com/564x/19/ec/a4/19eca4b44c9fe15db652b7b55d680228.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1t0RgsucVAkciyKQBW9BgIwAsIaWQVJ1Piw&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkVduSyJ-xqIe97L4T0XayhiMz78hmJE6aog&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqjtbEoke8xob7gIo1OvETc6EyS06WJilhgg&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYWAuIS1jlj4cpZktt1YB4A1COSIwospOepw&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjVD8bT1_I15GprnarMoMuxWgt7g3xz02gvQ&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpADnnW0yEMwiG8RwgEtLGn8Dd1UGJ5jQ1xw&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhjWZ6vwFIVXo2Cj20fy0iyIcjh3bJI1XlTQ&usqp=CAU",
        "https://abrakadabra.fun/uploads/posts/2021-12/1640852527_1-abrakadabra-fun-p-krutie-avi-dlya-devochek-v-vaiber-3.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRybiHsXWfdQAyAM1qHieNauTu4x_yiZvbeOw&usqp=CAU"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingContact = FragmentMyContactsBinding.inflate(layoutInflater,container,false)
        return bindingContact.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
        addContact()
        deleteBySwap()
    }

    override fun openContactProfile(
        position: Int,
        nameSurname: String,
        career: String,
        photo: String
    ) {

        val action = MyContactsFragmentDirections.actionMyContactsFragmentToContactFragmentProfile(
        nameSurname,career,photo
        )
        findNavController().navigate(
            action
        )
    }


    private fun addContact() {
        dialog = Dialog(requireContext())
        bindingContact.addContact.setOnClickListener{
            // bindingContact.txtVContacts.text = "sdf"
            dialog.setContentView(R.layout.dialog_add_contact)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val etNameSurname = dialog.et_name_surname_dialog
            val etCareer = dialog.et_career_dialog
            val btAddContact = dialog.add_contact_dialog

            btAddContact.setOnClickListener {

                val i = Random.nextInt(1,11)
                val contact = Contact(contactList.size-1,contactInfo[i],etNameSurname.text.toString(),
                    etCareer.text.toString())
                contactList.add(contactList.size, contact)
                writeListInPref(contactList)
                adapter.setList(getListInPref())
                Toast.makeText(requireActivity(),"Contact add successfully", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.show()
        }
    }


    private fun initial() {
        recyclerView = bindingContact.rvContact
        adapter = ContactAdapter(this)
        recyclerView.adapter = adapter
        if (getListInPref().isNotEmpty()){
            list = getListInPref()
            contactList = getListInPref()
        } else {
            list = myContact()
        }
        adapter.setList(list)
    }



    override fun deleteByPushOnBasket(position: Int) {
        bindingContact.txtVContacts.text = position.toString()
        returnContact = contactList[position]
        contactList.removeAt(position)
        writeListInPref(contactList)
        adapter.setList(getListInPref())
        snackbarIcon(position)
    }


    private fun deleteBySwap() {
        val swipeToDelete = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                bindingContact.txtVContacts.text = position.toString()
                returnContact = contactList[position]
                contactList.removeAt(position)
                writeListInPref(contactList)
                adapter.setList(getListInPref())
                snackbarIcon(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    fun snackbarIcon(position: Int) {
        Snackbar.make(bindingContact.rvContact, R.string.toast_delete, Toast.LENGTH_SHORT)
            .setAction("Return", object : View.OnClickListener {
                override fun onClick(view: View?) {
                    contactList.add(position, returnContact)
                    writeListInPref(contactList)
                    adapter.setList(getListInPref())
                    // костиль по поверненню контакта в книгу, замість повернення контакта,
                    // відмальовую всю книгу
                }
            }).show()
    }


    private fun myContact(): MutableList<Contact> {
        contactList = mutableListOf<Contact>()
        var i = 0
        while (i < contactInfo.size) {
            val info = Contact(i+1, contactInfo[i], "user namber ${i+1}", "carear ${i+1}")
            contactList.add(info)
            i++;
        }
        return contactList
    }


    val LIST_KAY = "list_kay"


    fun writeListInPref(list: MutableList<Contact>){
        val pref = this.requireActivity().getSharedPreferences("Table", Context.MODE_PRIVATE)
        val editor = pref.edit()
        val gson = Gson()
        val jsonString = gson.toJson(list)
        editor.putString(LIST_KAY, jsonString)
        editor.apply()
    }


    fun getListInPref():MutableList<Contact>{
        val pref = this.requireActivity().getSharedPreferences("Table", Context.MODE_PRIVATE)
        val jsonString = pref.getString(LIST_KAY,"")
        if(jsonString?.isEmpty()!!){
            return mutableListOf()
        }
        val gson = Gson()
        val type = object : TypeToken<MutableList<Contact>>(){}.type
        val list = gson.fromJson<MutableList<Contact>>(jsonString, type)
        return list
    }
}