non_primes(1).

add_range(L, R, S) :- L =< R, assert(non_primes(L)), L1 is L + S, add_range(L1, R, S).

gen_seq(L, R) :- L =< R, \+ non_primes(L), L1 is L * L, add_range(L1, R, L).
gen_seq(L, R) :- L1 is L + 1,  L =< R, gen_seq(L1, R).

init(MAX_N) :- gen_seq(2, MAX_N).

prime(N) :- \+ non_primes(N).
composite(N) :- non_primes(N).

is_sorted([]).
is_sorted([H]) :- prime(H), !.
is_sorted([A, H | T]) :- A =< H, prime(A), is_sorted([H|T]).

search_loop(L, R, Result) :- L * L > R, !.
search_loop(L, R, Result) :- 0 is R mod L, Result is L, !.
search_loop(L, R, Result) :- L1 is L + 1, search_loop(L1, R, Result).

first(N, R) :- prime(N), R is N, !.
first(N, R) :- search_loop(2, N, R).

prime_divisors(1, []) :- !.
prime_divisors(N, [N]) :- prime(N), !.
prime_divisors(N, [H|T]) :- \+ number(N), !, prime(H), prime_divisors(R, T), is_sorted([H|T]), N is R * H.
prime_divisors(N, [H|T]) :- first(N, H), D is N / H, prime_divisors(D, T), is_sorted([H|T]).


lift_up(I, N, P, N1) :- P is I - 1, N is N1 - 1, !.
lift_up(I, N, P, CUR_N) :- prime(I), T is CUR_N + 1, I1 is I + 1, lift_up(I1, N, P, T), !.
lift_up(I, N, P, CUR_N) :- I1 is I + 1, lift_up(I1, N, P, CUR_N).

prime_index(P, I) :- number(P), !, prime(P), lift_up(2, I, P, 1).
prime_index(P, I) :- lift_up(2, I, P, 1).
