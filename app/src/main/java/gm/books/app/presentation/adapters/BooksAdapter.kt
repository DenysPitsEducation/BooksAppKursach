package gm.books.app.presentation.adapters

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gm.books.app.databinding.BookItemBinding
import gm.books.domain.entities.Book

open class BooksAdapter :
    ListAdapter<Book, BooksAdapter.BookViewHolder>(DiffCallback) {

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
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        if (position == itemCount - 1) {
            setMarginToLastBook(holder)
        }
        holder.bind(item)
    }

    private fun setMarginToLastBook(holder: BookViewHolder) {
        val marginInDp = 16f
        val marginInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            marginInDp,
            holder.itemView.context.resources.displayMetrics
        )

        val marginLayoutParams =
            holder.binding.bookContainer.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.marginEnd = marginInPx.toInt()
    }

    open class BookViewHolder(
        val binding: BookItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(book: Book) {
            binding.apply {
                val coverUrl = book.coverUrl
                title.text = book.title
                Picasso.get().load(coverUrl).into(cover)
            }
        }
    }
}