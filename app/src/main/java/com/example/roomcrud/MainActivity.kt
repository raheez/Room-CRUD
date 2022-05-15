package com.example.roomcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomcrud.Entity.Notes
import com.example.roomcrud.ViewModel.NotesViewmodel
import com.example.roomcrud.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), notesClickInterface {
    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewmodel: NotesViewmodel
    lateinit var mAdapter: NotesAdapter
    lateinit var mList: List<Notes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)


        mList = mutableListOf()

        mViewmodel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(NotesViewmodel::class.java)

        initListener()
        setupObserver()

    }


    private fun initListener() {
        mBinding?.addNote?.setOnClickListener {
            val mTitle = mBinding?.titleEt?.text?.toString() ?: ""
            val mDescription = mBinding?.descriptionEt?.text?.toString() ?: ""


            if (!mTitle?.isEmpty() && !mDescription?.isEmpty()) {
                val mSDF = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDataAndTime: String = mSDF.format(Date())

                mBinding?.titleEt?.setText("")
                mBinding?.descriptionEt?.setText("")
                val mNote = Notes(mTitle, mDescription, currentDataAndTime)
                mViewmodel.addNote(mNote)

            }
        }

        mBinding?.deleteNote?.setOnClickListener {
            mViewmodel?.deleteNotes()
            Log.d("notes", "table deleted")
        }
    }

    private fun setupObserver() {
        mViewmodel?.mAllNotes?.observe(this, androidx.lifecycle.Observer {
            mList = it
            setRecycler()
        })
    }


    private fun setRecycler() {
        val mLinearLayoutMAnager = LinearLayoutManager(this)
        mAdapter = NotesAdapter(this, mList, this)
        mBinding?.recyclerViewNotes?.layoutManager = mLinearLayoutMAnager
        mBinding?.recyclerViewNotes?.adapter = mAdapter
    }

    override fun removeNotes(notes: Notes) {
        mViewmodel?.deleteNote(notes)
        Log.d("delete", "item")


    }

    override fun updateNotes(notes: Notes) {
        updateNote(notes)
    }

    private fun updateNote(notes: Notes) {

        val mNotes = notes
        mNotes.noteTitle = mBinding?.titleEt?.text?.toString()!!
        mNotes.noteDescription = mBinding?.descriptionEt?.text?.toString()!!
        mViewmodel?.updateNotes(mNotes)

    }

}