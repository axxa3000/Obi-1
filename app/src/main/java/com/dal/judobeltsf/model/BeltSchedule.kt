package com.dal.judobeltsf.model


enum class BeltSchedule( val months: IntArray) {
    // zero its because older judokas doesnt 've intermediate belts
    //Month between belt upgrade
    //-----------------------( b/a, a,  a/n,   n, n/v,  v, v/a,  a, a/m,   m, n_c,  n_s ),
    AGE_FOUR     ( intArrayOf(  12, 12,  12,  12,  12, 12,  12, 12,  12,  12,  12,  12  )),
    AGE_FIVE     ( intArrayOf(  12, 12,  12,  12,  12, 12,  12, 12,  12,  12,  12,  12  )),
    AGE_SIX      ( intArrayOf(   6,  6,  12,  12,  12, 12,  12, 12,  12,  12,  12,  12  )),
    AGE_SEVEN    ( intArrayOf(   6,  6,   6,   6,  12, 12,  12, 12,  12,  12,  12,  12  )),
    AGE_EIGHT    ( intArrayOf(   6,  6,   6,   6,   6,  6,  12, 12,  12,  12,  12,  12  )),
    AGE_NINE     ( intArrayOf(   6,  6,   6,   6,   6,  6,   6,  6,  12,  12,  12,  12  )),
    AGE_TEN      ( intArrayOf(   4,  4,   4,   6,   6,  6,   6,  6,   6,  12,  12,  12  )),
    AGE_ELEVEN   ( intArrayOf(   3,  3,   3,   3,   6,  6,   6,  6,   6,   6,  12,  12  )),
    AGE_TWELVE   ( intArrayOf(   2,  2,   2,   2,   2,  2,   6,  6,   6,   6,  12,  12  )),
    AGE_THIRTEEN ( intArrayOf(   2,  2,   2,   2,   2,  2,   3,  3,   3,   3,  12,  12  )),
    AGE_FOURTEEN ( intArrayOf(   2,  2,   2,   2,   2,  2,   0,  6,   0,   6,  12,  12  )),
    AGE_FIFTEEN  ( intArrayOf(   0,  4,   0,   4,   0,  4,   0,  6,   0,   6,  12,  12  ))
}

// beltSchedule2: Numbers are months between belt upgrade.
// Zero its because older judokas doesnt 've intermediate belts
val beltSchedule2 = arrayOf(
    //--------------( b/a, a,  a/n,   n, n/v,  v, v/a,  a, a/m,   m, n_c,  n_s ),
          intArrayOf(  12, 12,  12,  12,  12, 12,  12, 12,  12,  12,  12,  12  ),
          intArrayOf(  12, 12,  12,  12,  12, 12,  12, 12,  12,  12,  12,  12  ),
          intArrayOf(   6,  6,  12,  12,  12, 12,  12, 12,  12,  12,  12,  12  ),
          intArrayOf(   6,  6,   6,   6,  12, 12,  12, 12,  12,  12,  12,  12  ),
          intArrayOf(   6,  6,   6,   6,   6,  6,  12, 12,  12,  12,  12,  12  ),
          intArrayOf(   6,  6,   6,   6,   6,  6,   6,  6,  12,  12,  12,  12  ),
          intArrayOf(   4,  4,   4,   6,   6,  6,   6,  6,   6,  12,  12,  12  ),
          intArrayOf(   3,  3,   3,   3,   6,  6,   6,  6,   6,   6,  12,  12  ),
          intArrayOf(   2,  2,   2,   2,   2,  2,   6,  6,   6,   6,  12,  12  ),
          intArrayOf(   2,  2,   2,   2,   2,  2,   3,  3,   3,   3,  12,  12  ),
          intArrayOf(   2,  2,   2,   2,   2,  2,   0,  6,   0,   6,  12,  12  ),
          intArrayOf(   0,  4,   0,   4,   0,  4,   0,  6,   0,   6,  12,  12  )
)

val beltColor = arrayOf<String>(
    "B/A",
    "A",
    "A/N",
    "N",
    "N/V",
    "V",
    "V/A",
    "A",
    "A/M",
    "M",
    "N_con_C",
    "N_sin_C"
)