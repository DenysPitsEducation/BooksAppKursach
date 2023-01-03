package gm.books.app.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import gm.books.app.databinding.BookItemBinding
import gm.books.app.presentation.fragments.MainFragmentDirections
import gm.books.domain.entities.Book


class BooksMainAdapter : BooksAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BooksMainViewHolder(
        BookItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    class BooksMainViewHolder(private val bookBinding: BookItemBinding) :
        BooksAdapter.BookViewHolder(bookBinding) {
        override fun bind(book: Book) {
            super.bind(book)
            bookBinding.root.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(book.id)
                it.findNavController().navigate(action)
            }
        }
    }
}