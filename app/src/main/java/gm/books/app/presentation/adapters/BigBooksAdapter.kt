package gm.books.app.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gm.books.app.databinding.BigBookItemBinding
import gm.books.domain.entities.Book

class BigBooksAdapter :
    ListAdapter<Book, BigBooksAdapter.BookViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder(
            BigBookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        //holder.itemView.layoutParams = (LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        holder.bind(book)
    }

    fun getItemByPosition(position: Int): Book {
        if (position < 0) throw IndexOutOfBoundsException()
        return currentList[position]
    }

    open class BookViewHolder(
        private val binding: BigBookItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(book: Book) {
            binding.apply {
                val coverUrl = book.coverUrl
                Picasso.get().load(coverUrl).into(cover)
            }
        }
    }
}