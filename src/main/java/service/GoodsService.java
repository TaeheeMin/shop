package service;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import dao.*;
import util.DBUtil;
import vo.*;

public class GoodsService {
	private GoodsDao goodsDao;
	// 1) add goods
	public int addGoods(Goods goods, GoodsImg goodsImg, String dir) {
		Connection conn = null;
		this.goodsDao = new GoodsDao();
		GoodsImgDao goodsImgDao = new GoodsImgDao(); 
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			HashMap<String, Integer> map = goodsDao.insertGoods(conn, goods);
			goodsImg.setGoodsCode(map.get("autoKey")); 
			row = goodsImgDao.insertGoodsImg(conn, goodsImg);
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
				File f = new File(dir+"\\"+goodsImg.getFilename());
				if(f.exists()) {
					f.delete();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 2) list
	// 2-1) list count
	public int getGoodsListCount() {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			row = goodsDao.selectGoodsCount(conn);
			conn.commit(); // DBUtil setAutoCommit false설정
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 2-2) list
	public ArrayList<HashMap<String, Object>> getGoodsList(int currentPage, int rowPerPage, String search, String word, String category) {
		Connection conn = null;
		this.goodsDao = new GoodsDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBUtil.getConnection();
			// 페이징
			int beginRow = (currentPage - 1) * rowPerPage; // 시작 행
			// System.out.println("beginRow : " + beginRow);
			list = goodsDao.selectGoodsList(conn, beginRow, rowPerPage, search, word, category);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-2-2) 홈화면에 띄울 최신곡
	public ArrayList<HashMap<String, Object>> selectRecentlySongList() {
		Connection conn = null;
		this.goodsDao = new GoodsDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// System.out.println("[goodsService]");
		try {
			conn = DBUtil.getConnection();
			list = goodsDao.selectRecentlySongList(conn);
			// System.out.println("list : "+list);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-2-3) 홈화면에 띄울 히트곡
	public ArrayList<HashMap<String, Object>> selectHitSongList() {
		Connection conn = null;
		this.goodsDao = new GoodsDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// System.out.println("[goodsService]");
		try {
			conn = DBUtil.getConnection();
			list = goodsDao.selectHitSongList(conn);
			// System.out.println("list : "+list);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-2-4) 홈화면 밑부분에 소개할 해외음악
	public ArrayList<HashMap<String, Object>> selectPopSongList() {
		Connection conn = null;
		this.goodsDao = new GoodsDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// System.out.println("[goodsService]");
		try {
			conn = DBUtil.getConnection();
			list = goodsDao.selectPopSongList(conn);
			// System.out.println("팝송list service : "+list);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-2-5) 홈화면 밑부분에 소개할 인디음악
		public ArrayList<HashMap<String, Object>> selectIndieSongList() {
			Connection conn = null;
			this.goodsDao = new GoodsDao();
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			try {
				conn = DBUtil.getConnection();
				list = goodsDao.selectIndieSongList(conn);
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		
	// 2-3) goods one
	public ArrayList<HashMap<String, Object>> getGoodsOne(int goodsCode) {
		Connection conn = null;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			if(goodsDao.selectGoodsOne(conn, goodsCode) != null) {
				list = goodsDao.selectGoodsOne(conn, goodsCode);
				int row = this.goodsDao.updateGoodsView(conn, goodsCode);
			}
			conn.commit(); // DBUtil setAutoCommit false설정
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-4) goods one
	public Goods goodsOne(int goodsCode) {
		Goods goodsOne = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			goodsOne = goodsDao.GoodsOne(conn, goodsCode);
			conn.commit(); // DBUtil setAutoCommit false설정
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goodsOne;
	}
	
	// 2-5) goods one review
	public ArrayList<HashMap<String, Object>> getGoodsReview(int goodsCode) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			list = goodsDao.goodsReview(conn, goodsCode);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-6) 홈화면에 띄울 추천가수
	public ArrayList<Goods> getGoodsArtist() {
		Connection conn = null;
		ArrayList<Goods> list = new ArrayList<Goods>();
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			list = goodsDao.goodsArtist(conn);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 3) modify goods
	public int modifyGoods(Goods goods) {
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			row = goodsDao.updateGoods(conn, goods);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
