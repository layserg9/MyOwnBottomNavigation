package com.vlados.myownbottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vlados.myownbottomnavigation.databinding.RandomListBinding

class ZooFragment : Fragment() {
    private var zooAdapter = ZooAdapter(::deleteItem)
    private var bindingClass: RandomListBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private var contentType: Int = ANIMALS_CONTENT

    companion object {
        private const val CONTENT_TYPE_KEY = "ContentTypeKey"
        const val ANIMALS_CONTENT = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        bindingClass = RandomListBinding.inflate(inflater, container, false)
        return bindingClass?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingClass = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentType = arguments?.getInt(CONTENT_TYPE_KEY) ?: ANIMALS_CONTENT
        bindingClass?.recyclerViewRandom?.layoutManager = LinearLayoutManager(requireContext())
        bindingClass?.recyclerViewRandom?.adapter = zooAdapter
        bindingClass?.buttonRandom?.text = viewModel.getButtonName(contentType)
        zooAdapter.updateList(viewModel.getItems(contentType))
        bindingClass?.buttonRandom?.setOnClickListener {
            addItem()
            val scrollPosition = zooAdapter.itemCount.minus(1) ?: 0
            bindingClass?.recyclerViewRandom?.scrollToPosition(scrollPosition)
        }
    }

    private fun addItem() {
        val newList = viewModel.addItem(contentType)
        zooAdapter.updateList(newList)
    }

    private fun deleteItem(item: ZooItem): Boolean {
        val newList = viewModel.deleteItem(item, contentType)
        zooAdapter.updateList(newList)
        return true
    }
}