package tes.ajaxtest3;
import java.util.List;

public class Anime {
	//リスト
	private List<AnimeList> anime;
	
	//アニメリストの内部
	class AnimeList {
		private String title;
		private String outline;
		private String hash_tag;

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getOutline() {
			return outline;
		}
		public void setOutline(String outline) {
			this.outline = outline;
		}
		public String getHash_tag() {
			return hash_tag;
		}
		public void setHash_tag(String hash_tag) {
			this.hash_tag = hash_tag;
		}
	}

	public List<AnimeList> getAnime() {
		return anime;
	}

	public void setAnime(List<AnimeList> anime) {
		this.anime = anime;
	}
}