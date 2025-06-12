# List of refactorings

1. Extract Method `amountFor`.
2. Rename variable `thisAmount` to `result` in `amountFor` method.
3. Rename parameter `perf` to `aPerformance` in `amountFor` method.
4. Join declaration and assignment of `thisAmount` in `print` method.
5. Replace Temporary Variable with Query in `print` method.

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



## Coding standards

“coding standard to always call the return value from a function “result”. That way I always know its role”


“it’s useful to keep track of types—hence, my default name for a parameter includes the type name. I use an indefinite article with it unless there is some specific role information to capture in the name. I learned this convention from Kent Beck [Beck SBPP] and continue to find it helpful.”

Excerpt From
Refactoring: Improving the Design of Existing Code, Second Edition (Garner McCloud's Library)
Martin Fowler
This material may be protected by copyright.
