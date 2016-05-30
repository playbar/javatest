package pattern.behavior.template;

/// <summary>
/// 浮点数类型的冒泡算法
/// </summary>
public class FloatBubbleSorter extends BubbleSorter
{
    private float[] array = null;

    /// <summary>
    /// 用冒泡算法排序
    /// </summary>
    /// <param name="theArray"></param>
    /// <returns></returns>
    public int Sort(float[] theArray)
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
        float temp = array[index];
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



