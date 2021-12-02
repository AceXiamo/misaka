package com.misaka.utils2;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author z
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/** 空字符串 */
	private static final String NULLSTR = "";

	/** 下划线 */
	private static final char SEPARATOR = '_';

	/**
	 * 获取参数不为空值
	 *
	 * @param value defaultValue 要判断的value
	 * @return value 返回值
	 */
	public static <T> T nvl(T value, T defaultValue) {
		return value != null ? value : defaultValue;
	}

	/**
	 * * 判断一个Collection是否为空， 包含List，Set，Queue
	 *
	 * @param coll 要判断的Collection
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return isNull(coll) || coll.isEmpty();
	}

	/**
	 * * 判断一个Collection是否非空，包含List，Set，Queue
	 *
	 * @param coll 要判断的Collection
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * * 判断一个对象数组是否为空
	 *
	 * @param objects 要判断的对象数组 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects) {
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * * 判断一个对象数组是否非空
	 *
	 * @param objects 要判断的对象数组
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	/**
	 * * 判断一个Map是否为空
	 *
	 * @param map 要判断的Map
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	/**
	 * * 判断一个Map是否为空
	 *
	 * @param map 要判断的Map
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * * 判断一个字符串是否为空串
	 *
	 * @param str String
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(String str) {
		return isNull(str) || NULLSTR.equals(str.trim());
	}

	/**
	 * * 判断一个字符串是否为非空串
	 *
	 * @param str String
	 * @return true：非空串 false：空串
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * * 判断一个对象是否非空
	 *
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * * 判断一个对象是否是数组类型（Java基本型别的数组）
	 *
	 * @param object 对象
	 * @return true：是数组 false：不是数组
	 */
	public static boolean isArray(Object object) {
		return isNotNull(object) && object.getClass().isArray();
	}

	/** 去空格 */
	public static String trim(String str) {
		return (str == null ? "" : str.trim());
	}

	/**
	 * 截取字符串
	 *
	 * @param str 字符串
	 * @param start 开始
	 * @return 结果
	 */
	public static String substring(final String str, int start) {
		if (str == null) {
			return NULLSTR;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return NULLSTR;
		}

		return str.substring(start);
	}

	/**
	 * 截取字符串
	 *
	 * @param str 字符串
	 * @param start 开始
	 * @param end 结束
	 * @return 结果
	 */
	public static String substring(final String str, int start, int end) {
		if (str == null) {
			return NULLSTR;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return NULLSTR;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * 字符串转set
	 *
	 * @param str 字符串
	 * @param sep 分隔符
	 * @return set集合
	 */
	public static final Set<String> str2Set(String str, String sep) {
		return new HashSet<String>(str2List(str, sep, true, false));
	}

	/**
	 * 字符串转list
	 *
	 * @param str 字符串
	 * @param sep 分隔符
	 * @param filterBlank 过滤纯空白
	 * @param trim 去掉首尾空白
	 * @return list集合
	 */
	public static final List<String> str2List(
			String str, String sep, boolean filterBlank, boolean trim) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isEmpty(str)) {
			return list;
		}

		// 过滤空白字符串
		if (filterBlank && StringUtils.isBlank(str)) {
			return list;
		}
		String[] split = str.split(sep);
		for (String string : split) {
			if (filterBlank && StringUtils.isBlank(string)) {
				continue;
			}
			if (trim) {
				string = string.trim();
			}
			list.add(string);
		}

		return list;
	}

	/** 下划线转驼峰命名 */
	public static String toUnderScoreCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (i > 0) {
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			} else {
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1)) {
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
				sb.append(SEPARATOR);
			} else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 是否包含字符串
	 *
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
	 *
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String convertToCamelCase(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母大写
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String[] camels = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1).toLowerCase());
		}
		return result.toString();
	}

	/** 驼峰式命名法 例如：user_name->userName */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		return (T) obj;
	}

	/**
	 * 字符空NULL判断 @Title: isNullEmpty @Description: TODO
	 *
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	public static Boolean notNullAndEmpty(Object obj) {
		return (obj != null && !"".equals(obj) && !"null".equals(obj));
	}

	/**
	 * unicode转汉字 @Title: decodeUnicode @Description: TODO
	 *
	 * @param @param theString
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len; ) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	/**
	 * 空值检查
	 *
	 * @param pInput 要检查的字符串
	 * @return boolean 返回检查结果,但传入的字符串为空的场合,返回真
	 */
	public static boolean isNull(Object pInput) {
		// 判断参数是否为空或者’’
		if (pInput == null || "’’".equals(pInput) || "".equals(pInput) || "null".equals(pInput)) {
			return true;
		} else if ("java.lang.String".equals(pInput.getClass().getName())) {
			// 判断传入的参数的String类型的
			// 替换各种空格
			String tmpInput =
					Pattern.compile("[\\r|\\n|\\u3000]").matcher((String) pInput).replaceAll("");
			// 匹配空
			return Pattern.compile("^(\\s)*$").matcher(tmpInput).matches();
		} else {
			// 方法类
			Method method = null;
			try {
				// 访问传入参数的size方法
				method = pInput.getClass().getMethod("size");
				// 判断size大小
				// size为0的场合
				if (Integer.parseInt(String.valueOf(method.invoke(pInput))) == 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				try {
					// 访问传入参数的getItemCount方法
					method = pInput.getClass().getMethod("getItemCount");
					// 判断size大小
					// getItemCount为0的场合
					if (Integer.parseInt(String.valueOf(method.invoke(pInput))) == 0) {
						return true;
					} else {
						return false;
					}
				} catch (Exception ex) {
					try {
						// 判断传入参数的长度
						// 长度为0的场合
						if (Array.getLength(pInput) == 0) {
							return true;
						} else {
							return false;
						}
					} catch (Exception exx) {
						try {
							// 访问传入参数的hasNext方法
							method = Iterator.class.getMethod("hasNext");
							// 转换hasNext的值
							return !Boolean.valueOf(String.valueOf(method.invoke(pInput))) ? true : false;
						} catch (Exception exxx) {
							// 以上场合不满足返回假
							return false;
						}
					}
				}
			}
		}
	}

	public static Long obj2Long(Object val) {
		if (notNullAndEmpty(val)) {
			return Long.valueOf(val.toString().trim());
		} else {
			return 0L;
		}
	}

	public static Float obj2Float(Object val) {
		if (notNullAndEmpty(val)) {
			return Float.parseFloat(val.toString().trim());
		} else {
			return 0F;
		}
	}

	/**
	 * Object to Double
	 *
	 * @param val
	 * @return Double
	 * @throws Exception
	 * @author z
	 * @see
	 */
	public static Double obj2Dou(Object val) {
		if (notNullAndEmpty(val)) {
			return Double.parseDouble(val.toString().trim());
		} else {
			return 0d;
		}
	}

	/**
	 * Object to int
	 *
	 * @param val
	 * @return Integer
	 * @throws Exception
	 * @author z
	 * @see
	 */
	public static int obj2Int(Object val) {
		if (notNullAndEmpty(val)) {
			return Integer.parseInt(val.toString().trim());
		} else {
			return 0;
		}
	}

	/**
	 * Object to String
	 *
	 * @param val
	 * @return String
	 * @throws Exception
	 * @author z
	 * @see
	 */
	public static String obj2Str(Object val) {
		if (notNullAndEmpty(val)) {
			return val.toString().trim();
		} else {
			return "";
		}
	}

	/**
	 * Object to boolean
	 *
	 * @param Object
	 * @return boolean
	 * @throws Exception
	 * @author z
	 * @see
	 */
	//	public static boolean obj2Bln(Object val){
	//		if(org.apache.commons.lang.xwork.StringUtils.equalsIgnoreCase("1", val.toString().trim())){
	//			return true;
	//		}else{
	//			return false;
	//		}
	//	}

	/**
	 * 返回唯一图片名称 @Title: createImgName
	 *
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String createImgName() {
		Random r = new Random();
		String name =
				new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + r.nextInt(100000) + ".jpg";
		return name;
	}

	public static String createApkName() {
		Random r = new Random();
		String name =
				new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + r.nextInt(100000) + ".apk";
		return name;
	}

	public static String creatUploadName(String prefix) {
		Random r = new Random();
		String name =
				new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
						+ r.nextInt(100000)
						+ prefix;
		return name;
	}
	/**
	 * 获取文件后缀
	 *
	 * @param fileName
	 * @return
	 * @author z
	 * @see
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/**
	 * 返回唯一名称字符串 @Title: createName
	 *
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String createName() {
		Random r = new Random();
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + r.nextInt(100000);
		return name;
	}

	/**
	 * 将两个数组合并成一个数组
	 *
	 * @param first
	 * @param second
	 * @return
	 * @author z
	 * @see
	 */
	public static String[] concat(String[] first, String[] second) {
		if (isNull(first) && isNull(second)) {
			return null;
		}
		if (isNull(first) && !isNull(second)) {
			return second;
		}
		if (!isNull(first) && isNull(second)) {
			return first;
		}
		String[] both = (String[]) ArrayUtils.addAll(first, second);
		return both;
	}

	/**
	 * 字符串数组合并
	 *
	 * @param first
	 * @param second
	 * @return
	 * @author z
	 * @see
	 */
	public static String[] concat2(String[] first, String[] second) {
		//		if (isNull(first) && isNull(second)) {
		//			return null;
		//		}
		//		if(isNull(first) && !isNull(second)) {
		//			return second;
		//		}
		//		if(!isNull(first) && isNull(second)) {
		//			return first;
		//		}
		String[] both = new String[first.length + second.length];
		System.arraycopy(first, 0, both, 0, first.length);
		System.arraycopy(second, 0, both, first.length, second.length);
		return both;
	}

	/**
	 * 可将多个数组合并
	 *
	 * @param first
	 * @param rest
	 * @return
	 * @author z
	 * @see
	 */
	//	public static T[] concatAll(T[] first, T[]... rest) {
	//		int totalLength = first.length;
	//		for (T[] array : rest) {
	//			totalLength += array.length;
	//		}
	//		T[] result = Arrays.copyOf(first, totalLength);
	//		int offset = first.length;
	//		for (T[] array : rest) {
	//			System.arraycopy(array, 0, result, offset, array.length);
	//			offset += array.length;
	//		}
	//		return result;
	//	}

	/**
	 * 将字符串转换成数组
	 *
	 * @param str
	 * @return
	 * @author z
	 * @see
	 */
	public static String[] toArray(String str) {
		if (isNull(str)) {
			return null;
		}
		StringTokenizer sTokenizer = new StringTokenizer(str, ",");
		List<String> sList = new ArrayList<String>();
		while (sTokenizer.hasMoreTokens()) {
			sList.add(sTokenizer.nextToken());
		}
		int size = sList.size();
		String[] result = new String[size];
		for (int i = 0; i < size; i++) {
			result[i] = sList.get(i);
		}
		return result;
	}

	public static void main(String[] args) {
		String strs = "25";
		String[] str = toArray(strs);
		int gidLen = str.length;
		Long[] gids = new Long[gidLen];
		for (int i = 0; i < gidLen; i++) {
			//System.out.println(">>> " + StringUtils.obj2Long(str[i]));
			gids[i] = StringUtils.obj2Long(str[i]);
			//System.out.println(gids[i]);
		}
		//		for (String string : str) {
		//			System.out.println(obj2Long(25));
		//		}

	}
}

