package cz.home.nbaplayers.library.usecase.domain

/**
 * Base interface for all use cases (sometimes also called interactors). Do not extend directly - use [SynchronousUseCase] or [SuspendUseCase].
 */
sealed interface UseCase<in I, out O>

/**
 * Base use case for use cases with synchronous invoke method.
 *
 * @see UseCase
 * @see SuspendUseCase
 */
interface SynchronousUseCase<in I, out O> : UseCase<I, O> {
    operator fun invoke(input: I): O
}

/**
 * Base use case for use cases which need `suspend` invoke method.
 *
 * @see UseCase
 * @see SynchronousUseCase
 * @see invokeAsFlow
 */
interface SuspendUseCase<in I, out O> : UseCase<I, O> {
    suspend operator fun invoke(input: I): O
}
