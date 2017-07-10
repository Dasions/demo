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
	 *  ��Ե����ַ����Ĵ洢�ķ�װ
	 * @param key
	 * @return
	 */
	public Object getValue(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * ����key-value
	 * @param key
	 * @param value
	 */
	public void setValue(String key,T value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * ����key-value�͹���ʱ��
	 * @param key
	 * @param value
	 * @param time
	 */
	public void setValueAndOutTime(String key,T value,long time){
		redisTemplate.opsForValue().set(key, (T) key, time, TimeUnit.SECONDS);
	}
	
	/**
	 * ��ȡ�洢���ݵ�ʣ�����ʱ��
	 * @param key
	 * @return
	 */
	public long getValueExpireTime(String key){
		return redisTemplate.getExpire(key);
	}
	
    /**
     * ���list�Ĵ洢�ķ�װ
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
	 * ���list�Ļ�ȡ�ķ�װ
	 * @param <T>
	 */
	public List<T> getListValue(String key){
		List<T> dataList = new ArrayList<T>();
        ListOperations<String,T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        
        for(int i = 0 ; i < size ; i ++)
        {
            dataList.add((T) listOperation.leftPop(key));
        }
        
        return dataList;
	}
	
	/**
	 * ���key-value�Ĵ洢
	 * @param map
	 */
	public void setKeysValues(Map<String, String> map){
		redisTemplate.opsForValue().multiSet((Map<? extends String, ? extends T>) map);
	}
	
	/**
	 * ���key-value�Ļ�ȡ
	 * @param keys
	 * @return
	 */
	public List<?> getKeysValues(List<String> keys){
		return redisTemplate.opsForValue().multiGet(keys);
	}
	
	/**
	 * ���map�Ĵ洢
	 * @param mapName
	 * @param map
	 */
	public void setMap(String mapName,Map<String, String> map){
		redisTemplate.opsForHash().putAll(mapName, map);
		//����
		//redisTemplate.opsForHash().put("redisHash","name","tom");
        //redisTemplate.opsForHash().put("redisHash","age",26);
        //redisTemplate.opsForHash().put("redisHash","class","6");
	}
	
	/**
	 * ɾ�������Ĺ�ϣhashKeys
	 * @return 1 ɾ���ɹ�
	 */
	public Long delKeyValue(String mapName,String key){
		return redisTemplate.opsForHash().delete(mapName, key);
	}
	
	/**
	 * ȷ��map���Ƿ����ָ����key
	 * @param mapName
	 * @param key
	 * @return true���ڣ�false������
	 */
	public Boolean isExistKey(String mapName,String key){
		return redisTemplate.opsForHash().hasKey(mapName, key);
	}
	
	/**
	 * ��ȡmap��key��value
	 * @param mapName
	 * @param key
	 * @return
	 */
	public String getMapKeyValue(String mapName,String key){
		return (String) redisTemplate.opsForHash().get(mapName, key);
	}
	
	/**
	 * ��ȡmap��keys
	 * @param mapName
	 * @return
	 */
	public Set<Object> getMapKeys(String mapName){
		return redisTemplate.opsForHash().keys(mapName);
	}
	
	/**
	 * ��ȡmap��key�ĸ���
	 * @param mapName
	 * @return
	 */
	public Long getMapKeysSize(String mapName){
		return redisTemplate.opsForHash().size(mapName);
	}
	
	/**
	 * �洢set
	 * @param setName
	 * @param strs
	 */
	public void addSetValues(String setName,String[] strs){
		redisTemplate.opsForSet().add(setName,  (T[])strs);
	}
	
	/**
	 * ɾ��set�е�ָ����ֵ
	 * @param setName
	 * @param strs
	 */
	public void delSetValues(String setName,String[] strs){
		redisTemplate.opsForSet().remove(setName,  (T[])strs);
	}
	
	/**
	 * ��ȡset�Ĵ�С
	 * @param setName
	 * @return
	 */
	public Long getSetSize(String setName){
		return redisTemplate.opsForSet().size(setName);
	}
	
	/**
	 * �ж�set���Ƿ����ĳ��ֵ
	 * @param setName
	 * @param memberName
	 * @return
	 */
	public Boolean isSetMember(String setName,String memberName){
		return redisTemplate.opsForSet().isMember(setName, memberName);
	}
	
	/**
	 * ��ȡ����set�Ľ���
	 * @param setNameA
	 * @param setNameB
	 * @return
	 */
	public Set<T> getIntersect(String setNameA,String setNameB){
		return redisTemplate.opsForSet().intersect(setNameA, setNameB);
	}
	
	/**
	 * ��ȡset��ȫ��ֵ
	 * @param setName
	 * @return
	 */
	public Set<T> getSetValues(String setName){
		return redisTemplate.opsForSet().members(setName);
	}
}
