## Task 1

1. Work with class `Animal`.
2. Provide it with 3 private fields - **color**(`String`), **numberOfPaws**(`int`), **hasFur**(`boolean`).
3. Add **constructor with full  parameters**. *Save the parameter order and names as it is listed in a second paragraph.*
4. Add a methods `getDescription()`, witch would use class fields and return a string with such pattern **"This animal is mostly `(color)`. It has `(numberOfPaws)` paws and `('a'/'no' -> depends on value of hasFur)` fur."**
5. **(Optional)**  *In the method `getDescription()` change the word **'paw'** depending on the `numberOfPaws`: number of paws is 1 -> 'paw', number of paws is different from 1 -> 'paws'.*

## Task 2

1. Work with classes `Dog` and `Bird`. **Extend** them with the help of `Animal`.
2. Create **no-args constructor** for each where provide all necessary information for `Animal` constructor by `super()` method:
   * for `Dog`: `color` - *brown*, `numberOfPaws` - *4*, `hasFur` - *true*;
   * for `Bird`: `color` - *blue*, `numberOfPaws` - *2*, `hasFur` - *false*.
3. Override `getDescription()` method for class `Bird`: add one more sentence to the description. The result must be **"This animal is mostly blue. It has 2 paws and no fur. Moreover, it has 2 wings and can fly."**
4. **(Optional)**  *Create an object of each class and call `getDescription()` method for both of them. Try to explain the output results.*