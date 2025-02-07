package org.jacob.jap.learning.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * ローマ字からひらがなに変換するユーティリティクラス
 * このクラスは、ユーザーが入力したローマ字をひらがなに変換するための機能を提供します。
 * ローマ字とひらがなの対応表を基に変換を行います。
 *
 * @author Kotohiko
 * @since 2025年2月7日 23:57
 */
public class HiraganaGenner {

    // ローマ字からひらがなへの変換マッピングを保持する定数
    private static final Map<String, String> romajiToHiragana = new HashMap<>();

    // 静的初期化ブロックで、ローマ字とひらがなのマッピングを設定
    static {
        String[][] mapping = {
                {"a", "あ"}, {"i", "い"}, {"u", "う"}, {"e", "え"}, {"o", "お"}, {"ka", "か"}, {"ki", "き"}, {"ku", "く"},
                {"ke", "け"}, {"ko", "こ"}, {"sa", "さ"}, {"shi", "し"}, {"su", "す"}, {"se", "せ"}, {"so", "そ"},
                {"ta", "た"}, {"chi", "ち"}, {"tsu", "つ"}, {"te", "て"}, {"to", "と"}, {"na", "な"}, {"ni", "に"},
                {"nu", "ぬ"}, {"ne", "ね"}, {"no", "の"}, {"ha", "は"}, {"hi", "ひ"}, {"fu", "ふ"}, {"he", "へ"},
                {"ho", "ほ"}, {"ma", "ま"}, {"mi", "み"}, {"mu", "む"}, {"me", "め"}, {"mo", "も"}, {"ya", "や"},
                {"yu", "ゆ"}, {"yo", "よ"}, {"ra", "ら"}, {"ri", "り"}, {"ru", "る"}, {"re", "れ"}, {"ro", "ろ"},
                {"wa", "わ"}, {"wo", "を"}, {"n", "ん"},
                // 浊音（濁音）の追加マッピング
                {"ga", "が"}, {"gi", "ぎ"}, {"gu", "ぐ"}, {"ge", "げ"}, {"go", "ご"}, {"za", "ざ"}, {"ji", "じ"},
                {"zu", "ず"}, {"ze", "ぜ"}, {"zo", "ぞ"}, {"da", "だ"}, {"ji", "ぢ"}, {"zu", "づ"}, {"de", "で"},
                {"do", "ど"}, {"ba", "ば"}, {"bi", "び"}, {"bu", "ぶ"}, {"be", "べ"}, {"bo", "ぼ"}, {"pa", "ぱ"},
                {"pi", "ぴ"}, {"pu", "ぷ"}, {"pe", "ぺ"}, {"po", "ぽ"},
                // 拗音（ようおん）の追加マッピング
                {"kya", "きゃ"}, {"kyu", "きゅ"}, {"kyo", "きょ"}, {"sha", "しゃ"}, {"shu", "しゅ"}, {"sho", "しょ"},
                {"cha", "ちゃ"}, {"chu", "ちゅ"}, {"cho", "ちょ"}, {"nya", "にゃ"}, {"nyu", "にゅ"}, {"nyo", "にょ"},
                {"hya", "ひゃ"}, {"hyu", "ひゅ"}, {"hyo", "ひょ"}, {"mya", "みゃ"}, {"myu", "みゅ"}, {"myo", "みょ"},
                {"rya", "りゃ"}, {"ryu", "りゅ"}, {"ryo", "りょ"}, {"gya", "ぎゃ"}, {"gyu", "ぎゅ"}, {"gyo", "ぎょ"},
                {"ja", "じゃ"}, {"ju", "じゅ"}, {"jo", "じょ"}, {"bya", "びゃ"}, {"byu", "びゅ"}, {"byo", "びょ"},
                {"pya", "ぴゃ"}, {"pyu", "ぴゅ"}, {"pyo", "ぴょ"}
        };

        // 配列の内容をマップに変換
        for (String[] pair : mapping) {
            romajiToHiragana.put(pair[0], pair[1]);
        }
    }

    /**
     * メインメソッド。ユーザーが入力したローマ字をひらがなに変換して表示します。
     * 無限ループで動作し、ユーザーが入力を続ける限り処理を繰り返します。
     *
     * @param args コマンドライン引数（使用しません）
     * @throws IOException 入力時の例外
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {
        // 入力を受け取るためのBufferedReaderを準備
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // ユーザーからローマ字を入力させ、ひらがなに変換して表示
            String romanSound = readInput(in);
            System.out.println(convertToHiragana(romanSound));
        }
    }

    /**
     * ユーザーからローマ字を入力させるメソッド。
     *
     * @param in 入力を受け取るためのBufferedReader
     * @return ユーザーが入力したローマ字の文字列
     * @throws IOException 入力時の例外
     */
    private static String readInput(BufferedReader in) throws IOException {
        System.out.print("ローマ字を入力してください: ");
        return in.readLine();
    }

    /**
     * ローマ字をひらがなに変換するメソッド。
     * ローマ字の文字列を1文字ずつ順番にチェックし、対応するひらがなを結果に追加します。
     * よく使われるのは1文字、2文字、または3文字のローマ字であり、それらを順に探します。
     *
     * @param romaji ローマ字の文字列
     * @return 変換されたひらがな文字列
     */
    public static String convertToHiragana(String romaji) {
        StringBuilder result = new StringBuilder();
        int length = romaji.length();

        // 文字列の長さに基づいて処理
        for (int i = 0; i < length; ) {
            boolean matched = false;

            // 最大3文字のローマ字をチェック
            for (int j = Math.min(3, length - i); j > 0; --j) {
                String sub = romaji.substring(i, i + j);
                if (romajiToHiragana.containsKey(sub)) {
                    result.append(romajiToHiragana.get(sub));
                    i += j;  // マッチした場合、iを進める
                    matched = true;
                    break;
                }
            }

            // マッチしなかった場合は「?」を追加して次の文字を進める
            if (!matched) {
                result.append("?");
                ++i;
            }
        }

        return result.toString();
    }
}
