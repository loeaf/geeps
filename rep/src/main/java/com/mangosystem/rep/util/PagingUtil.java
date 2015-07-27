package com.mangosystem.rep.util;

import java.util.Map;

import com.mangosystem.rep.resourceBundle.CommonConstants;
import com.mangosystem.rep.resourceBundle.SystemConstants;

public class PagingUtil {

	/**
	 * Get StartIndex
	 * 
	 * @param pageIndex
	 * @return the int value
	 */
	public static int getStartIndex(int pageIndex) {
		return getStartIndex(pageIndex, 10);
	}

	/**
	 * Get StartIndex
	 * 
	 * @param pageIndex
	 * @return the int value
	 */
	public static int getStartIndex(String pageIndex) {
		if (pageIndex == null || pageIndex.equals(""))
			pageIndex = "1";
		return getStartIndex(Integer.parseInt(pageIndex), 10);
	}

	/**
	 * Get StartIndex
	 * 
	 * @param pageIndex
	 * @param size
	 * @return the int value
	 */
	public static int getStartIndex(int pageIndex, int size) {
		if (pageIndex == 0)
			pageIndex = 1;
		int startIndex = (pageIndex - 1) * size + 1;
		return startIndex;
	}

	/**
	 * Get StartIndex
	 * 
	 * @param pageIndex
	 * @param size
	 * @return the int value
	 */
	public static int getStartIndex(String pageIndex, String size) {
		if (pageIndex == null || pageIndex.equals(""))
			pageIndex = "1";
		return getStartIndex(Integer.parseInt(pageIndex),
				Integer.parseInt(size));
	}

	/**
	 * Get EndIndex
	 * 
	 * @param pageIndex
	 * @return the int value
	 */
	public static int getEndIndex(int pageIndex) {
		return getEndIndex(pageIndex, 10);
	}

	/**
	 * Get EndIndex
	 * 
	 * @param pageIndex
	 * @param size
	 * @return the int value
	 */
	public static int getEndIndex(int pageIndex, int size) {
		if (pageIndex == 0)
			pageIndex = 1;
		int endIndex = pageIndex + size - 1;
		return endIndex;
	}

	/**
	 * Get EndIndex
	 * 
	 * @param pageIndex
	 * @return the int value
	 */
	public static int getEndIndex(String pageIndex) {
		if (pageIndex == null || pageIndex.equals(""))
			pageIndex = "1";
		return getEndIndex(Integer.parseInt(pageIndex), 10);
	}

	/**
	 * Get EndIndex
	 * 
	 * @param pageIndex
	 * @param size
	 * @return the int value
	 */
	public static int getEndIndex(String pageIndex, String size) {
		if (pageIndex == null || pageIndex.equals(""))
			pageIndex = "1";
		return getEndIndex(Integer.parseInt(pageIndex), Integer.parseInt(size));
	}

	/**
	 * GET Paging Values startIndex, endIndex || limitValue, offsetIndex
	 * 
	 * @param paramMap
	 * @param page
	 * @param listSize
	 */
	public static void getPagingValues(Map<String, Object> paramMap, int page,
			int listSize) {
		int startIndex = getStartIndex(page, listSize);
		int endIndex = getEndIndex(startIndex, listSize);
		int limitValue = listSize;
		int offsetIndex = startIndex - 1;

		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", endIndex);
		paramMap.put("limitValue", limitValue);
		paramMap.put("offsetIndex", offsetIndex);
	}

	/**
	 * GET Paging Values startIndex, endIndex || limitValue, offsetIndex
	 * 
	 * @param paramMap
	 * @param page
	 * @param listSize
	 */
	public static void getPagingValues(Map<String, Object> paramMap,
			String page, String listSize) {
		getPagingValues(paramMap, Integer.parseInt(page),
				Integer.parseInt(listSize));
	}

	/**
	 * Paging OffSet Value (DB.Postgresql)
	 * 
	 * @param page
	 * @return
	 */
	public static int getOffSetIndex(int page) {
		int startIndex = getStartIndex(page, CommonConstants.WEB_PAGING_DEFAULT_LIST_SIZE);
		return startIndex - 1;
	}
	
	/**
	 * Paging OffSet Value (DB.Postgresql)
	 * 
	 * @param page
	 * @param listSize
	 * @return
	 */
	public static int getOffSetIndex(int page, int listSize) {
		int startIndex = getStartIndex(page, listSize);
		return startIndex - 1;
	}

	/**
	 * Paging OffSet Value (DB.Postgresql)
	 * 
	 * @param page
	 * @param listSize
	 * @return
	 */
	public static int getOffSetIndex(String page, String listSize) {
		return getOffSetIndex(Integer.parseInt(page),
				Integer.parseInt(listSize));
	}

	/**
	 * Paging Limit Value (DB.Postgresql)
	 * 
	 * @return
	 */
	public static int getLimitValue() {
		return CommonConstants.WEB_PAGING_DEFAULT_LIST_SIZE;
	}
	
	/**
	 * Paging Limit Value (DB.Postgresql)
	 * 
	 * @param listSize
	 * @return
	 */
	public static int getLimitValue(int listSize) {
		return listSize;
	}

	/**
	 * Paging Limit Value (DB.Postgresql)
	 * 
	 * @param listSize
	 * @return
	 */
	public static int getLimitValue(String listSize) {
		return getLimitValue(Integer.parseInt(listSize));
	}

	/**
	 * 
	 * @param totalListSize
	 * @param pageIndex
	 * @param size
	 * @param pageView
	 * @param param
	 * @return
	 */
	public static String getPaging(int totalListSize, int pageIndex, int size, int pageView, String param) {
		int totalPageSize = 0; // 가지고온 게시물에 따른 페이지 크기
		int maxListSize = size; // 한페이지에 표시되는 게시물크기
		int endPage = 0; // 마지막 페이지
		int startPage = 0; // 시작페이지
		String minPage = "";
		String maxPage = "";

		if (pageIndex == 0)
			pageIndex = 1;

		totalPageSize = (totalListSize - 1) / maxListSize + 1;

		if (totalListSize == 0)
			totalPageSize = 0;
		if (totalListSize > 0) {
			startPage = ((pageIndex - 1) / pageView) * pageView + 1;
			endPage = ((pageIndex + pageView - 1) / pageView) * pageView;
			if (endPage > totalPageSize)
				endPage = totalPageSize;
		}

		if (startPage > pageView) {
			minPage = "<li><a href=\"" + param + (startPage - 1) + "\">«</a></li>";
		} else {
			minPage = "<li class=\"disabled\"><a href=\"#\">«</a></li>";
		}

		if (totalPageSize > endPage) {
			maxPage = "<li><a href=\"" + param + (endPage + 1) + "\">»</a></li>";
		} else {
			maxPage = "<li class=\"disabled\"><a href=\"#\">»</a></li>";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"row text-center\"><ul class=\"pagination\">");
		sb.append(minPage);

		for (int i = startPage; i <= endPage; i++) {
			// String nowPage = "<a href='" + param + i + "'>" + i + "</a>";
			String nowPage = "<li><a href=\"" + param + i + "\">" + i + "</a></li>";

			if (i == 0)
				nowPage = "1";

			// 선택 페이징 폰트 스타일 수정
			if (i == pageIndex)
				nowPage = "<li class=\"active\"><a href=\"#\">" + i + "</a></li>";

			if (i != endPage)
				nowPage += " ";

			sb.append(nowPage);
		}

		sb.append(maxPage);
		sb.append("</ul></div>");

		if (totalPageSize == 0) {
			return "";
		}

		return sb.toString();
	}

}