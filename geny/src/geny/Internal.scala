package geny

import java.io.{InputStream, OutputStream}

object Internal {
  def transfer0(src: InputStream,
                sink: (Array[Byte], Int) => Unit) = {
    val buffer = new Array[Byte](8192)
    var r = 0
    while (r != -1) {
      r = src.read(buffer)
      if (r != -1) sink(buffer, r)
    }
    src.close()
  }

  def transfer(src: InputStream, dest: OutputStream) = transfer0(
    src,
    dest.write(_, 0, _)
  )
}
