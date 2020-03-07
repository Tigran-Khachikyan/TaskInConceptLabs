package com.example.taskinconceptlabs.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskinconceptlabs.R
import com.example.taskinconceptlabs.ui.AdapterUsersRecycler
import kotlinx.android.synthetic.main.fragment_list.*

class UsersFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var adapter: AdapterUsersRecycler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_intro.text = requireContext().getString(R.string.all_the_users)

        adapter = AdapterUsersRecycler(null, usersViewModel)
        recycler_whole_users.setHasFixedSize(true)
        recycler_whole_users.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        recycler_whole_users.adapter = adapter

        usersViewModel.getUsers().observe(viewLifecycleOwner, Observer {

            adapter.users = it
            adapter.notifyDataSetChanged()
        })
    }
}