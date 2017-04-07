package Server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		DefaultFullHttpRequest req = (DefaultFullHttpRequest) msg;

		HttpServer.rpcServer.handle(new ByteBufInputStream(req.content()),
				new ByteBufOutputStream(req.content()));

		writeJSON(ctx, HttpResponseStatus.OK, req.content());
		ctx.flush();
//		System.out.println(req.getMethod());
	}

	private static void writeJSON(ChannelHandlerContext ctx, HttpResponseStatus status, ByteBuf content){
		if (ctx.channel().isWritable()) {
			FullHttpResponse msg = null;
			if (content != null) {
				msg = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
						content);
				msg.headers().set(HttpHeaders.Names.CONTENT_TYPE,
						"application/json; charset=utf-8");
			} else {
				msg = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
			}
			if (msg.content() != null) {
				msg.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
						msg.content().readableBytes());
			}
			// not keep-alive
			ctx.write(msg).addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx,
								   FullHttpRequest msg) throws Exception {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
	}
}
