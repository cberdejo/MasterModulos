
import asyncio
import random
import time
from typing import Any

from pydantic import BaseModel


def func():
    print("sync")

    # Try to avoid
    #result = asyncio.run(a_func_with_error_return(1))

class Result(BaseModel):
    value: Any | None = None
    error: str | None = None



async def a_func_with_error_return(i) -> Result:
    """
    
    Returns the input the value, or a string with a description of the error"""
    await asyncio.sleep(random.randint(0, i))
    print(f"async {i}")
    if random.randint(0, 10) > 9:
        return Result(value=None, error="Error aleatorio 10%, mala suerte!")
    return Result(value=i, error = None)

async def a_func_with_exception(i) -> int:
    """
    
    Returns the input the value, or a string with a description of the error"""
    await asyncio.sleep(random.randint(0, i))
    print(f"async {i}")
    if random.randint(0, 10) > 9:
        raise ValueError("Error aleatorio 10%, mala suerte!")
    
    return i

async def a_generator():
    """An asynchronous generator, to consume it you you must
    use async for instead of a regular for
    
    ```
    async for element in a_generator():
        print(element)
    ```
    """
    yield 1
    yield 2
    yield 3



async def main():
    tasks = []
    for i in range(20):
        tasks.append(a_func_with_error_return(i))


    #results = await asyncio.gather(*tasks)
    #for task in results:
    #    print("Corregir", task)

    fut = asyncio.to_thread(func)

    await fut
    for task in asyncio.as_completed(tasks):
        task = await task

        if task.error:
            pass
            # Error control here
        
        value = task.value
        print("Corregir", task)


if __name__ == "__main__":

    #raise ValueError("Test")

    asyncio.run(main())