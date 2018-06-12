package main.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import main.db.entity.Contact
import zlotnikov.architecturecomponents.R
import zlotnikov.architecturecomponents.databinding.ContactListItemBinding


class ContactAdapter : PagedListAdapter<Contact, ContactAdapter.ContactViewHolder>(
        object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                    oldItem.firstName == newItem.firstName && oldItem.lastName == oldItem.lastName
                            && oldItem.post == newItem.post

        }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ContactListItemBinding = DataBindingUtil.inflate(inflater, R.layout.contact_list_item, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ContactViewHolder(private val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.contact = contact
        }
    }
}