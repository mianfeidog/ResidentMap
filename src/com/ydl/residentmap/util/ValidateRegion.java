package com.ydl.residentmap.util;

public class ValidateRegion {
	/**
	 * 获取点所在的省份
	 * 
	 * @param lat
	 * @param lon
	 * @return
	 *//*
	public String getEquipCurrentRegion(float lat, float lon) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		if (region == null) {
			region = new HashMap();
			try {
				String sql = "select * from regionContry";// 需要新建区域表

				System.out.println("设备卡片信息****************" + sql + "********");
				System.out.println(sql);
				conn = PoolManagerForErp.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Blob blob = rs.getBlob("points");
					String points = new String(blob.getBytes((long) 1, (int) blob.length()));
					region.put(rs.getString("name"), points);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.toString());
			} finally {
				try {
					if (null != rs) {
						rs.close();
						rs = null;
					}
					if (null != pstmt) {
						pstmt.close();
						pstmt = null;
					}
					PoolManagerForErp.freeConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 第一种 用for循环的方式
		for (Map.Entry<String, String> m : region.entrySet()) {
			String[] latLngs = m.getValue().split(";");
			float[] pointLats = new float[latLngs.length];
			float[] pointLons = new float[latLngs.length];
			int i = 0;
			for (String ll : latLngs) {
				try {
					String latStr = ll.split(",")[1];
					String lonStr = ll.split(",")[0];
					pointLats[i] = (Float.parseFloat(latStr));
					pointLons[i] = (Float.parseFloat(lonStr));
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (PointInPolygon(pointLats, pointLons, lat, lon)) {
				result = m.getKey();
				break;
			}
		}
		return result;
	}

	*//**
	 * 获取点所在的省份
	 * 
	 * @param lat
	 * @param lon
	 * @return
	 *//*
	public String getEquipCurrentRegionFromFile(float lat, float lon) {
		String result = "";
		if (region == null) {
			region = new HashMap();
			String fileName = getClass().getResource("/").getFile().toString() + "chinaregiondata.txt";
			File file = new File(fileName);
			BufferedReader reader = null;
			StringBuilder sb = null;
			try {
				System.out.println("以行为单位读取文件内容，一次读一整行：");
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");  
				reader = new BufferedReader(isr); 
				String tempString = null;
				sb = new StringBuilder();
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					sb.append(tempString);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
			for (String regionStr : sb.toString().split("]")) {
				try {
					if (regionStr == null || regionStr.equals("")) {
						continue;
					}
					String[] rStrs = regionStr.split("&");
					region.put(rStrs[0], rStrs[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 第一种 用for循环的方式
		for (Map.Entry<String, String> m : region.entrySet()) {
			String[] latLngs = m.getValue().split(";");
			float[] pointLats = new float[latLngs.length];
			float[] pointLons = new float[latLngs.length];
			int i = 0;
			for (String ll : latLngs) {
				String latStr = ll.split(",")[1];
				String lonStr = ll.split(",")[0];
				pointLats[i] = (Float.parseFloat(latStr));
				pointLons[i] = (Float.parseFloat(lonStr));
				i++;
			}
			if (PointInPolygon(pointLats, pointLons, lat, lon)) {
				result = m.getKey();
				break;
			}
		}
		return result;
	}*/

	/**
	 * 点在多边形内部
	 * 
	 * @param pointLats
	 * @param pointLons
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static boolean PointInPolygon(float[] pointLats, float[] pointLons, float lat, float lon) {
		boolean result = false;
		int count = pointLons.length;

		if (count < 3) {
			return false;
		}

		for (int i = 0, j = count - 1; i < count; i++) {
			float p1Lat = pointLats[i];
			float p1Lon = pointLons[i];
			float p2Lat = pointLats[j];
			float p2Lon = pointLons[j];

			if (p1Lat < lat && p2Lat >= lat || p2Lat < lat && p1Lat >= lat) {
				if (p1Lon + (lat - p1Lat) / (p2Lat - p1Lat) * (p2Lon - p1Lon) < lon) {
					result = !result;
				}
			}
			j = i;
		}
		return result;
	}
	
	public static void main(String[] args) {
		float[] pointLats = new float[4];
		float[] pointLons = new float[4];
		pointLats[0] =116.285836f;
		pointLons[0]= 39.970696f;
		
		pointLats[1] =116.504304f;
		pointLons[1]= 39.976888f;
		
		pointLats[2] =116.479008f;
		pointLons[2]= 39.861787f;
		
		pointLats[3] =116.305383f;
		pointLons[3]= 39.857799f;
		
		float lat=116.305383f;
		float lon =39.857797f;
		
		System.out.println("该经纬度是否在区域内："+PointInPolygon(pointLats, pointLons, lat, lon));
	}
	
}
