package gm.books.app.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import gm.books.app.R
import gm.books.app.databinding.DetailsFragmentBinding
import gm.books.app.presentation.activities.MainActivity
import gm.books.app.presentation.adapters.BigBooksAdapter
import gm.books.app.presentation.adapters.BooksDetailsAdapter
import gm.books.app.presentation.decorations.HorizontalMarginItemDecoration
import gm.books.app.presentation.utils.injectViewModel
import gm.books.app.presentation.viewmodels.DetailsViewModel
import gm.books.app.presentation.viewmodels.FirebaseResponseViewModel
import gm.books.domain.entities.Book
import kotlin.math.abs


class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var firebaseViewModel: FirebaseResponseViewModel
    private val navigationArgs: DetailsFragmentArgs by navArgs()
    private lateinit var binding: DetailsFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = injectViewModel((activity as MainActivity).viewModelFactory)
        firebaseViewModel = injectViewModel((activity as MainActivity).viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BooksDetailsAdapter(binding)
        binding.youLikeRecycler.adapter = adapter

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val bigAdapter = BigBooksAdapter()
        val viewPager = binding.bigBookPager
        viewPager.adapter = bigAdapter
        viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.2f * abs(position))
            page.scaleX = 1 - (0.2f * abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.currentSlide = position
                val book = bigAdapter.getItemByPosition(position)
                binding.bindInfoAboutBook(book)
            }
        })

        firebaseViewModel.firebaseResponse.observe(viewLifecycleOwner) { firebaseResponse ->
            if (firebaseResponse != null) {
                val startPosition = viewModel.currentSlide ?: navigationArgs.bookId

                bigAdapter.submitList(firebaseResponse.books) {
                    viewPager.currentItem = startPosition
                }

                adapter.submitList(firebaseResponse.books.filter {
                    firebaseResponse.likeIds.contains(
                        it.id
                    )
                })
            }
        }
    }
}

fun DetailsFragmentBinding.bindInfoAboutBook(book: Book) {
    title.text = book.title
    author.text = book.author
    readersCount.text = book.views.lowercase()
    likesCount.text = book.likes.lowercase()
    quotesCount.text = book.quotes.lowercase()
    genreValue.text = book.genre
    summary.text = book.summary
}