package pattern.behavior.template;

/// <summary>
/// 整型类型的冒泡算法实现
/// </summary>
public class IntBubbleSorter extends BubbleSorter
{
    private int[] array = null;

    /// <summary>
    /// 用冒泡算法排序
    /// </summary>
    /// <param name="theArray"></param>
    /// <returns></returns>
    public int Sort(int[] theArray)
    {
        array = theArray;
        length = array.length;
        // 调用冒泡算法
        return DoSort();
    }

    /// <summary>
    /// 实现冒泡算法中的交换操作
    /// </summary>
    /// <param name="index"></param>
    protected void Swap(int index)
    {
        int temp = array[index];
        array[index] = array[index + 1];
        array[index + 1] = temp;
    }

    /// <summary>
    /// 实现冒泡算法中的比较操作
    /// </summary>
    /// <param name="index"></param>
    /// <returns></returns>
    protected Boolean OutOfOrder(int index)
    {
        return (array[index] > array[index + 1]);
    }
}

