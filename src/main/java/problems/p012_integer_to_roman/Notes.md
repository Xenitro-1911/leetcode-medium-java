# 12. Integer to Roman

## Approach — Hardcoded Place-Value Lookup Tables

Pre-build four string arrays, one per decimal place (thousands, hundreds, tens, units).
Each array is indexed 0–9, where the index is the digit at that place value.
The Roman representation is then just a concatenation of four direct lookups.

## Walkthrough

Given `num = 1994`:

| Place     | Digit | Lookup            | Result  |
|-----------|-------|-------------------|---------|
| thousands | 1     | thousands[1]      | `M`     |
| hundreds  | 9     | hundreds[9]       | `CM`    |
| tens      | 9     | tens[9]           | `XC`    |
| units     | 4     | units[4]          | `IV`    |

Final: `M` + `CM` + `XC` + `IV` = `MCMXCIV`

Digit extraction:
- thousands → `num / 1000`
- hundreds  → `num % 1000 / 100`
- tens      → `num % 100 / 10`
- units     → `num % 10`

## Complexity

- **Time**: O(1) — fixed four lookups regardless of input
- **Space**: O(1) — fixed-size static arrays

## Alternative Approach — Greedy with Parallel Arrays

Use two parallel arrays of all 13 values (including subtractive combos like 900, 400, 90, etc.).
Loop top to bottom: while `num >= values[i]`, append `symbols[i]` and subtract.

```java
int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
StringBuilder sb = new StringBuilder();
int i = 0;
while (num != 0) {
    if (num >= values[i]) {
        sb.append(symbols[i]);
        num -= values[i];
    } else {
        i++;
    }
}
return sb.toString();
```

- Also O(1) time/space, but slightly more iterations than the lookup table approach.

## Common Mistakes

- Using `>` instead of `>=` in the greedy approach — causes infinite loop when `num == values[i]`
- Using two separate `if` instead of `if/else if` — redundantly re-checks after appending
- Using `String +=` in a loop instead of `StringBuilder`
- Forgetting index 0 in each lookup array must be `""` (empty string for digit = 0)

## Key Insights

- Subtractive forms (IV, IX, XL, XC, CD, CM) can be pre-baked — no special casing needed
- The lookup table approach works because the constraint guarantees `1 <= num <= 3999`, so each digit is always 0–9
- `static final` arrays are initialized once at class load — no per-call overhead