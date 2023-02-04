package com.geektech.homework53

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.homework53.databinding.ItemImageBinding

class ImageAdapter(): RecyclerView.Adapter<ImageAdapter.ImageHolder>() {
    var list :ArrayList<ImageModel> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun addList(arrayList: List<ImageModel>) {
        list.addAll(arrayList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ImageHolder(val binding: ItemImageBinding): ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
            binding.imageV.load(imageModel.largeImageURL)
        }

    }
}