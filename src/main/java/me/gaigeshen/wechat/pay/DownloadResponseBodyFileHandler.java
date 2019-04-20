package me.gaigeshen.wechat.pay;

import java.io.*;

/**
 * 下载文件请求结果处理器，可以指定下载到文件
 *
 * @author gaigeshen
 */
public class DownloadResponseBodyFileHandler<R extends Response> extends DownloadResponseBodyHandler<R> {
  /**
   * 创建下载文件请求结果处理器
   *
   * @param failedResultType 下载失败的时候的结果类型
   * @param file 下载成功时需要写出的文件
   */
  public DownloadResponseBodyFileHandler(Class<R> failedResultType, File file) {
    super(failedResultType, bytes -> {
      try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
        out.write(bytes);
      } catch (IOException e) {
        throw new IllegalStateException("Could not write to file: " + file);
      }
    });
  }
}
