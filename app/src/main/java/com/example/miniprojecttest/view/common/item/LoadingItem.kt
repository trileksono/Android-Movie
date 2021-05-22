package com.example.miniprojecttest.view.common.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.miniprojecttest.R
import com.example.miniprojecttest.databinding.ItemLoadingBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class LoadingItem : AbstractBindingItem<ItemLoadingBinding>() {

    override val type: Int get() = R.id.item_loading_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemLoadingBinding {
        return ItemLoadingBinding.inflate(inflater, parent, false)
    }
}