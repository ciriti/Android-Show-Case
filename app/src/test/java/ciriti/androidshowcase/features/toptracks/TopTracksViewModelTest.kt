@file:Suppress("IllegalIdentifier")

package ciriti.androidshowcase.features.toptracks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.getFlatTrack
import ciriti.androidshowcase.features.BaseState
import ciriti.androidshowcase.features.CustomState
import ciriti.androidshowcase.features.DefaultState
import ciriti.androidshowcase.features.ErrorState
import ciriti.androidshowcase.features.LoadingState
import ciriti.datalayer.exception.NoNetworkException
import ciriti.datalayer.network.TopTrack
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import util.createGsonObj

/**
 * Created by ciriti
 */
class TopTracksViewModelTest {

  @Rule @JvmField val rule = InstantTaskExecutorRule()
  @Rule fun mokitoRules() = MockitoJUnit.rule()
  @Mock lateinit var topTracksUseCase: TopTracksUseCase
  @Mock lateinit var observer: Observer<BaseState>

  val viewModel by lazy { TopTracksViewModel(topTracksUseCase) }

  @Before
  fun setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
  }

  @Test
  fun `test to observe the track update`() {

    /** creating a list from a jason file */
    val list = "top_tracks.json".createGsonObj<TopTrack>()
        .tracks.list.map { it.getFlatTrack() }

    /** set the list to the mock object */
    Mockito.`when`(topTracksUseCase.observeTopTrackList())
        .thenReturn(Flowable.just(list))

    /** object under test */
    viewModel.observeTopTracks()

    /** verify the result */
    Assert.assertTrue(viewModel.liveData.value is DefaultState)

  }

  @Test
  fun `load data from the server`() {

    /** creating a list from a jason file */
    val list = "top_tracks.json".createGsonObj<TopTrack>()
        .tracks.list.map { it.getFlatTrack() }

    /** set the list to the mock object */
    Mockito.`when`(topTracksUseCase.updateTopTracks(limit = list.size))
        .thenReturn(Completable.complete())

    /** object under test */
    viewModel.loadTracks(list.size)

    /** verify the result */
    Assert.assertTrue(viewModel.liveData.value is LoadingState)
    Assert.assertFalse(viewModel.liveData.value is CustomState)

  }

  @Test
  fun `load data from the server with exception`() {

    /** creating a list from a jason file */
    val list = "top_tracks.json".createGsonObj<TopTrack>()
        .tracks.list.map { it.getFlatTrack() }

    /** set the list to the mock object */
    Mockito.`when`(topTracksUseCase.updateTopTracks(limit = list.size))
        .thenReturn(Completable.error(NoNetworkException()))

    /** object under test */
    viewModel.loadTracks(list.size)

    /** verify the result */
    Assert.assertTrue(viewModel.liveData.value is ErrorState)
    Assert.assertFalse(viewModel.liveData.value is CustomState)
    /** check the right message related to the exception */
    Assert.assertEquals(R.string.offline_active, (viewModel.liveData.value as ErrorState).errorMessage)

  }

  @Test
  fun `load data from the server tested with ArgumentCaptor`() {

    /** creating a list from a jason file */
    val list = "top_tracks.json".createGsonObj<TopTrack>()
        .tracks.list.map { it.getFlatTrack() }

    /** set the list to the mock object */
    Mockito.`when`(topTracksUseCase.updateTopTracks(limit = list.size))
        .thenReturn(Completable.complete())

    viewModel.liveData.observeForever(observer)
    viewModel.loadTracks(list.size)

    val argCaptor = ArgumentCaptor.forClass(BaseState::class.java)

    argCaptor.run {
      Mockito.verify(observer, Mockito.times(3))
          .onChanged(capture())
      val (defaultState, loadingStateTrue, loadingStateFalse) = allValues
      Assert.assertTrue(defaultState is DefaultState)
      Assert.assertTrue((loadingStateTrue as LoadingState).isLoading)
      Assert.assertFalse((loadingStateFalse as LoadingState).isLoading)
      /** All necessary test */
    }

  }

}