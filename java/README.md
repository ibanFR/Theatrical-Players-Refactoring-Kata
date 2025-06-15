# List of refactorings

1. Extract Method `amountFor`.
2. Rename variable `thisAmount` to `result` in `amountFor` method.
3. Rename parameter `perf` to `aPerformance` in `amountFor` method.
4. Join declaration and assignment of `thisAmount` in `print` method.
5. Replace Temporary Variable `play` with Query in `print` method.
6. Inline Variable `play` in `print` method.
7. ~~Change Function Declaration for `amountFor` method.~~
8. Inline variable `thisAmount` in `print` method.
9. Extract Method `volumeCredits` from `print` method:
   - Inline method `playFor` in `print` method.
   - Introduce variable `play` in `print` method.
   - Slide statement.
   - Extract method `volumeCreditsFor` from `print` method.
   - Split variable/remove assignments to `volumeCredits` parameter in `volumeCreditsFor` method.
   - Change function declaration - remove parameter.
10. Removing the `frmt` variable in `print` method:
    - Put arguments on separate lines.
    - Inline variable `frmt` in `print` method.
    - Extract method `usd` from `print` method.
11. Removing Total Volume Credits:
    - Split loop in `print` method.
    - Use Slide statement to move the declaration of the variable next to the loop.
    - Extract method `totalVolumeCredits` from `print` method.
    - Inline variable `volumeCredits` in `print` method.


## Instructions
0. “test after every refactoring, however simple” => CONTROL + R

Excerpt From
Refactoring: Improving the Design of Existing Code, Second Edition (Garner McCloud's Library)
Martin Fowler
This material may be protected by copyright.

1. 
2. “take a look at what I’ve extracted to see if there are any quick and easy things I can do to clarify the extracted
   function.” 
3. “look in the fragment for any variables that will no longer be in scope”
4. OPTION + ENTER
5. “consider the parameters to amountFor, I look to see where they come from. aPerformance comes from the loop variable, so naturally changes with each iteration through the loop. But play is computed from the performance, so there’s no need to pass it in as a parameter at all—I can just recalculate it within amountFor”
6. 
7. not worth it in Java
8. “The great benefit of removing local variables is that it makes it much easier to do extractions, since there is less local scope to deal with. Indeed, usually I’ll take out local variables before I do any extractions.”
9. First attempt to extract `volumeCreditsFor` method shows that I need to pass `plays` parameter because it is used by the extracted code in the `playFor` method.
   - It seems that there is very little value in passing all the `plays` to the `volumeCreditsFor` method, so I will inline the `playFor` method in the `print` method as a preparation for the extraction of `volumeCreditsFor` method.
10. 




## Coding standards

“coding standard to always call the return value from a function “result”. That way I always know its role”


“it’s useful to keep track of types—hence, my default name for a parameter includes the type name. I use an indefinite article with it unless there is some specific role information to capture in the name. I learned this convention from Kent Beck [Beck SBPP] and continue to find it helpful.”

Excerpt From
Refactoring: Improving the Design of Existing Code, Second Edition (Garner McCloud's Library)
Martin Fowler
This material may be protected by copyright.
