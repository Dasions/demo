package commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil<T> {
	
	@Autowired
	private RedisTemplate<String, T> redisTemplate;
	
	/**
	 *  针对当个字符串的存储的封装
	 * @param key
	 * @return
	 */
	public Object getValue(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 设置key-value
	 * @param key
	 * @param value
	 */
	public void setValue(String key,T value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 设置key-value和过期时间
	 * @param key
	 * @param value
	 * @param time
	 */
	public void setValueAndOutTime(String key,T value,long time){
		redisTemplate.opsForValue().set(key, (T) key, time, TimeUnit.SECONDS);
	}
	
	/**
	 * 获取存储数据的剩余过期时间
	 * @param key
	 * @return
	 */
	public long getValueExpireTime(String key){
		return redisTemplate.getExpire(key);
	}
	
    /**
     * 针对list的存储的封装
     * rightPush()会在列表的尾部添加一个元素， 而leftPush()则会在列表的头部添加一个值
     * @param key
     * @param dataList
     */
	public <T> void setListValue(String key,List<T> dataList){
		if(dataList!=null){
			ListOperations<String, T> listOperation=(ListOperations<String, T>) redisTemplate.opsForList();
			int size=dataList.size();
			
			for(int i=0;i<size;i++){
				listOperation.rightPush(key,dataList.get(i));
			}
			
		}
	}
	
	/**
	 * 针对list的获取的封装
	 * 通过leftPop()或rightPop()方法从列表中弹出一个元素，从列表中移除所弹出的元素
	 * @param <T>
	 */
	public List<T> getListValue(String key){
		List<T> dataList = new ArrayList<T>();
        ListOperations<String,T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        
        for(int i = 0 ; i < size ; i ++)
        {
            dataList.add((T) listOperation.leftPop(key));
            //或者
            //dataList.add((T) listOperation.rightPop(key));
        }
        
        return dataList;
	}
	
	/**
	 * 针对list的获取的封装
	 * 根据指定的key和索引范围， 获取范围内的一个或多个值,不会删除取出的元素
	 * @param listName
	 * @param start
	 * @param end
	 * @return
	 */
	public List<T> getListValue(String listName,Integer start,Integer end){
		ListOperations<String,T> listOperation = redisTemplate.opsForList();
		return listOperation.range(listName, start, end);
	}
	/**
	 * 针对key-value的存储
	 * @param map
	 */
	public void setKeysValues(Map<String, String> map){
		redisTemplate.opsForValue().multiSet((Map<? extends String, ? extends T>) map);
	}
	
	/**
	 * 针对key-value的获取
	 * @param keys
	 * @return
	 */
	public List<?> getKeysValues(List<String> keys){
		return redisTemplate.opsForValue().multiGet(keys);
	}
	
	/**
	 * 针对map的存储
	 * @param mapName
	 * @param map
	 */
	public void setMap(String mapName,Map<String, String> map){
		redisTemplate.opsForHash().putAll(mapName, map);
		//或者
		//redisTemplate.opsForHash().put("redisHash","name","tom");
        //redisTemplate.opsForHash().put("redisHash","age",26);
        //redisTemplate.opsForHash().put("redisHash","class","6");
	}
	
	/**
	 * 删除给定的哈希hashKeys
	 * @return 1 删除成功
	 */
	public Long delKeyValue(String mapName,String key){
		return redisTemplate.opsForHash().delete(mapName, key);
	}
	
	/**
	 * 确认map中是否存在指定的key
	 * @param mapName
	 * @param key
	 * @return true存在，false不存在
	 */
	public Boolean isExistKey(String mapName,String key){
		return redisTemplate.opsForHash().hasKey(mapName, key);
	}
	
	/**
	 * 获取map中key的value
	 * @param mapName
	 * @param key
	 * @return
	 */
	public String getMapKeyValue(String mapName,String key){
		return (String) redisTemplate.opsForHash().get(mapName, key);
	}
	
	/**
	 * 获取map的keys
	 * @param mapName
	 * @return
	 */
	public Set<Object> getMapKeys(String mapName){
		return redisTemplate.opsForHash().keys(mapName);
	}
	
	/**
	 * 获取map的key的个数
	 * @param mapName
	 * @return
	 */
	public Long getMapKeysSize(String mapName){
		return redisTemplate.opsForHash().size(mapName);
	}
	
	/**
	 * 存储set,针对批量的元素存储
	 * @param setName
	 * @param strs
	 */
	public void addSetValues(String setName,String[] strs){
		redisTemplate.opsForSet().add(setName,  (T[])strs);
	}
	
	/**
	 * 删除set中的指定的值
	 * @param setName
	 * @param strs
	 */
	public void delSetValues(String setName,String[] strs){
		redisTemplate.opsForSet().remove(setName,  (T[])strs);
	}
	
	/**
	 * 获取set的大小
	 * @param setName
	 * @return
	 */
	public Long getSetSize(String setName){
		return redisTemplate.opsForSet().size(setName);
	}
	
	/**
	 * 判断set中是否存在某个值
	 * @param setName
	 * @param memberName
	 * @return
	 */
	public Boolean isSetMember(String setName,String memberName){
		return redisTemplate.opsForSet().isMember(setName, memberName);
	}
	
	/**
	 * 获取两个set的交集
	 * @param setNameA
	 * @param setNameB
	 * @return
	 */
	public Set<T> getIntersect(String setNameA,String setNameB){
		return redisTemplate.opsForSet().intersect(setNameA, setNameB);
	}
	
	/**
	 * 获取两个set的差异元素
	 * @param setNameA
	 * @param setNameB
	 * @return
	 */
	public Set<T> getDifference(String setNameA,String setNameB){
		return redisTemplate.opsForSet().difference(setNameA, setNameB);
	}
	
	/**
	 * 获取两个set的并集
	 * @param setNameA
	 * @param setNameB
	 * @return
	 */
	public Set<T> getUnion(String setNameA,String setNameB){
		return redisTemplate.opsForSet().union(setNameA, setNameB);
	}
	
	/**
	 * 获取set的全部值
	 * @param setName
	 * @return
	 */
	public Set<T> getSetValues(String setName){
		return redisTemplate.opsForSet().members(setName);
	}
}
