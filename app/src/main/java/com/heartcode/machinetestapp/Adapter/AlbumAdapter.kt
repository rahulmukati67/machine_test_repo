package com.heartcode.machinetestapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heartcode.machinetestapp.Album
import com.heartcode.machinetestapp.databinding.ListItemAlbumBinding

class AlbumAdapter : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ListItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
    }

    // Inflates the item layout and returns a new instance of AlbumViewHolder.

    class AlbumViewHolder(private val binding: ListItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.album = album
            binding.executePendingBindings()
        }
    }

    /*
     * DiffUtil.ItemCallback implementation for comparing Album objects in RecyclerView.
     * This class defines two methods:
     * - areItemsTheSame compares if two items have the same id.
     * - areContentsTheSame compares if two items have the same contents.
     */
    private class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }
}
