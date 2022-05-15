package com.example.roomcrud

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrud.Entity.Notes
import com.example.roomcrud.databinding.AdapterNotesItemBinding

class NotesAdapter(context: Context, list: List<Notes>,clickInterface: notesClickInterface) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    val mContext = context
    var mList = list
    var mClickInterface = clickInterface
    class NotesViewHolder(val binding: AdapterNotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = AdapterNotesItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val mNote = mList.get(position)
        if (mNote != null) {
            holder?.binding?.titleTv?.text = mNote?.noteTitle
            holder?.binding?.descriptionTv?.text = mNote?.noteDescription
        }

        holder?.binding?.container?.setOnClickListener {
            mClickInterface?.updateNotes(mList?.get(position))
        }

    }

    override fun getItemCount(): Int {

        return mList.size
    }
}