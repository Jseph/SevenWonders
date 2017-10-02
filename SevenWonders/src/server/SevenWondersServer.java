/*
 * Copyright 2015, gRPC Authors All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Logger;
import proto.GameServerGrpc;
import proto.JoinRoomReply;
import proto.JoinRoomRequest;
import proto.Player;

public class SevenWondersServer {
  private static final Logger logger = Logger.getLogger(SevenWondersServer.class.getName());

  private Server server;

  private void start() throws IOException {
    /* The port on which the server should run */
    int port = 50051;
    server = ServerBuilder.forPort(port).addService(new GameServerImpl()).build().start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread() {
              @Override
              public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                SevenWondersServer.this.stop();
                System.err.println("*** server shut down");
              }
            });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /** Await termination on the main thread since the grpc library uses daemon threads. */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /** Main launches the server from the command line. */
  public static void main(String[] args) throws IOException, InterruptedException {
    final SevenWondersServer server = new SevenWondersServer();
    server.start();
    server.blockUntilShutdown();
  }

  static class GameServerImpl extends GameServerGrpc.GameServerImplBase {

    TreeMap<String, ArrayList<Player>> rooms;

    public GameServerImpl() {
      rooms = new TreeMap<>();
    }

    @Override
    public void joinRoom(JoinRoomRequest req, StreamObserver<JoinRoomReply> responseObserver) {
      if (!rooms.containsKey(req.getRoomName())) {
        rooms.put(req.getRoomName(), new ArrayList<>());
      }
      ArrayList<Player> players = rooms.get(req.getRoomName());
      for (int i = 0; i < players.size(); i++) {
        if (players.get(i).getName().equals(req.getPlayer().getName())) {
          players.remove(i--);
        }
      }
      players.add(req.getPlayer());
      JoinRoomReply reply = JoinRoomReply.newBuilder().build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}
