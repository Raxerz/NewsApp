package com.raxerz.news.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.raxerz.news.rules.RxJavaImmediateSchedulerRule
import com.raxerz.news.data.api.NewsApi
import com.raxerz.news.data.model.NewsItem
import com.raxerz.news.data.model.SingleNewsItem
import com.raxerz.news.data.model.Source
import com.raxerz.news.data.repository.NewsListRepository
import com.raxerz.news.data.repository.NewslistRemoteRepository
import com.raxerz.news.ui.viewmodels.NewsListViewModel
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import javax.inject.Inject
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class NewsListViewModelTest {
    private lateinit var viewModel: NewsListViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField var testSchedulerRule = RxJavaImmediateSchedulerRule()


    private lateinit var mMockRepository: NewslistRemoteRepository

    @Inject
    lateinit var mNewsApi: NewsApi

    @Before
    @Throws(Exception::class)
    public fun setUp() {
        MockitoAnnotations.initMocks(this)
        initializeViewModelWithData()
    }

    fun initializeViewModelWithData(){
        mMockRepository = NewslistRemoteRepository(mNewsApi)
        val source = Source(null, "The Times of India")
        val newsList = SingleNewsItem(
            source,
            "Varsha Balakrishnan",
            "Post-COVID recovery and heart attacks: Is there a link? - Times of India",
            "An Oxford-led study conducted recently established that over 50%, or 5 out of every 10 COVID-19 patients with a se","https://timesofindia.indiatimes.com/life-style/health-fitness/health-news/post-covid-recovery-and-heart-attacks-is-there-a-link/articleshow/85890206.cms\"", "\"https://static.toiimg.com/thumb/msid-85890206,width-1070,height-580,overlay-toi_sw,pt-32,y_pad-40,resizemode-75,imgsize-38974/85890206.jpg\"",
            "2021-09-03T05:52:00Z",
            "*Priyansh Rai was just over 35 when he suffered his first heart attack. While he had been confident about leading a 'healthy lifestyle, the heart attack completely shocked him, which came up weeks af…")
        val newsItem = NewsItem("ok", 38, arrayListOf(newsList))
        `when`(mNewsApi.getItems()).thenReturn(Single.just(newsItem))
        viewModel = NewsListViewModel(mMockRepository)
    }

    @Test
    fun shouldShowTitle() {
        val title = viewModel.getNewsItems().value?.data?.articles?.get(0)?.title
        assertEquals("Post-COVID recovery and heart attacks: Is there a link? - Times of India", title)
    }
}