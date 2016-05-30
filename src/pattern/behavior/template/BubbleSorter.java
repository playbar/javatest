package pattern.behavior.template;

/// <summary>
/// 抽象类，定义冒泡排序的骨架
/// </summary>
public abstract class BubbleSorter
{
    private int operations = 0;
    protected int length = 0;

    /// <summary>
    /// 冒泡排序算法
    /// </summary>
    /// <returns></returns>
    protected int DoSort()
    {
        operations = 0;
        if (length <= 1)
        {
            return operations;
        }

        for (int nextToLast = length - 2; nextToLast >= 0; nextToLast--)
        {
            for (int index = 0; index <= nextToLast; index++)
            {
                if (OutOfOrder(index))
                {
                    Swap(index);
                }

                operations++;
            }
        }

        return operations;
    }

    /// <summary>
    /// 留给子类实现的交换位置方法
    /// </summary>
    /// <param name="index"></param>
    protected abstract void Swap(int index);
    /// <summary>
    /// 留给子类实现的比较方法
    /// </summary>
    /// <param name="index"></param>
    /// <returns></returns>
    protected abstract Boolean OutOfOrder(int index);
    
}
