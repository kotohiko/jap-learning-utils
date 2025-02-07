package org.jacob.jap.learning.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.jacob.jap.learning.utils.HiraganaGenner.convertToHiragana;

/**
 *
 * @author Kotohiko
 * @since 00:10 Feb 08, 2025
 */
public class HiraganaGennerTest {
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
}