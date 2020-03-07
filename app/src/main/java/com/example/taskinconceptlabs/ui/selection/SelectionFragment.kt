package com.example.taskinconceptlabs.ui.selection

import android.os.Bundle
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

class SelectionFragment : Fragment() {

    private lateinit var selectionViewModel: SelectionViewModel
    private lateinit var adapter: AdapterUsersRecycler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectionViewModel = ViewModelProvider(this).get(SelectionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_intro.text = requireContext().getString(R.string.chosen)

        adapter = AdapterUsersRecycler(null, selectionViewModel)
        recycler_whole_users.setHasFixedSize(true)
        recycler_whole_users.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false)
        recycler_whole_users.adapter = adapter

        selectionViewModel.getChosenUsers()?.observe(viewLifecycleOwner, Observer {

            adapter.users = it
            adapter.notifyDataSetChanged()
        })
    }
}