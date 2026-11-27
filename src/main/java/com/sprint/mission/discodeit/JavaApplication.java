package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;

import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {
        // 0 - 1. Repository 생성
        UserRepository userRepository = new FileUserRepository();
        ChannelRepository channelRepository = new FileChannelRepository();
        MessageRepository messageRepository = new FileMessageRepository();

        // 0. 서비스 생성
        UserService userService = new BasicUserService(userRepository);
        ChannelService channelService = new BasicChannelService(channelRepository);
        MessageService messageService = new BasicMessageService(messageRepository, channelRepository, userRepository);

        // 1. 사용자 서비스 테스트
        printTitle("USER");

        // 1 - 1. 등록
        printStep(1, "등록");
        User createUser = userService.create("kangdev9401", "kangdev9401@gmail.com", "1q2w3e4r!!");
        User modifyUser = userService.create("kangdev9402", "kangdev9402@gmail.com", "1q2w3e4r!!");
        System.out.println("등록 결과 : " + createUser);
        System.out.println("등록 결과 : " + modifyUser);

        // 1 - 2. 조회
        printStep(2, "조회 (단건, 다건)");

        System.out.println("[단건 조회]");
        System.out.println(userService.find(createUser.getId()));
        System.out.println("[다건 조회]");
        userService.findAll().forEach(System.out::println);

        // 1 - 3. 수정
        printStep(3, "수정");
        userService.update(modifyUser.getId(), "kangdev940TWO", "kangdev940TWO@gmail.com", "1q2w3e4r5t!!");
        System.out.println("수정 완료 : ID = " + modifyUser.getId());

        // 1 - 4. 수정된 데이터 조회
        printStep(4, "수정된 데이터 조회");
        System.out.println(userService.find(modifyUser.getId()));

        // 1 - 5. 삭제
        printStep(5, "삭제");
        userService.delete(modifyUser.getId());
        System.out.println("삭제 완료 : ID = " + modifyUser.getId());

        // 1 - 6. 삭제된 데이터 확인
        printStep(6, "삭제된 데이터 확인");
        testException(
                "삭제된 회원 정보 조회",
                IllegalArgumentException.class,
                () -> userService.find(modifyUser.getId())
        );
        System.out.println("잔여 데이터 : " + userService.findAll().size() + "건");
        userService.findAll().forEach(System.out::println);

        // 1 - 7. 예외 처리 테스트
        printStep(7, "예외 처리 테스트");

        // 존재하지 않는 사용자 조회
        testException(
                "존재하지 않는 사용자 조회",
                IllegalArgumentException.class,
                () -> userService.find(UUID.randomUUID())
        );

        printEnd("USER");

        // 2. 채널 서비스 테스트
        printTitle("CHANNEL");

        // 2 - 1. 등록
        printStep(1, "등록");
        Channel createChannel = channelService.create(ChannelType.PUBLIC, "공지방", "공지사항 방입니다.");
        Channel modifyChannel = channelService.create(ChannelType.PRIVATE, "비밀의방", "비밀의 방입니다.");
        System.out.println("등록 결과 : " + createChannel);
        System.out.println("등록 결과 : " + modifyChannel);

        // 2 - 2. 조회
        printStep(2, "조회 (단건, 다건)");

        System.out.println("[단건 조회]");
        System.out.println(channelService.find(createChannel.getId()));
        System.out.println("[다건 조회]");
        channelService.findAll().forEach(System.out::println);

        // 2 - 3. 수정
        printStep(3, "수정");
        channelService.update(modifyChannel.getId(), ChannelType.PRIVATE, "다락방", "다락방입니다.");
        System.out.println("수정 완료 : ID = " + modifyChannel.getId());

        // 2 - 4. 수정된 데이터 조회
        printStep(4, "수정된 데이터 조회");
        System.out.println(channelService.find(modifyChannel.getId()));

        // 2 - 5. 삭제
        printStep(5, "삭제");
        channelService.delete(modifyChannel.getId());
        System.out.println("삭제 완료 : ID = " + modifyChannel.getId());

        // 2 - 6. 삭제된 데이터 확인
        printStep(6, "삭제된 데이터 확인");
        testException(
                "삭제된 채널 정보 조회",
                IllegalArgumentException.class,
                () -> channelService.find(modifyChannel.getId())
        );
        System.out.println("잔여 데이터 : " + channelService.findAll().size() + "건");
        channelService.findAll().forEach(System.out::println);

        // 2 - 7. 예외 처리 테스트
        printStep(7, "예외 처리 테스트");

        // 존재하지 않는 채널 조회
        testException(
                "존재하지 않는 채널 조회",
                IllegalArgumentException.class,
                () -> channelService.find(UUID.randomUUID())
        );

        printEnd("CHANNEL");

        // 3. 메시지 서비스 테스트
        printTitle("MESSAGE");

        // 3 - 1. 등록
        printStep(1, "등록");
        Message createMessage = messageService.create("안녕하십니까?", createChannel.getId(), createUser.getId());
        Message modifyMessage = messageService.create("수정될 메시지 입니다.", createChannel.getId(), createUser.getId());
        System.out.println("등록 결과 : " + createMessage);
        System.out.println("등록 결과 : " + modifyMessage);

        // 3 - 2. 조회
        printStep(2, "조회 (단건, 다건)");

        System.out.println("[단건 조회]");
        System.out.println(messageService.find(createMessage.getId()));
        System.out.println("[다건 조회]");
        messageService.findAll().forEach(System.out::println);

        // 3 - 3. 수정
        printStep(3, "수정");
        messageService.update(modifyMessage.getId(), "내용 수정을 진행하겠습니다.", createChannel.getId(), createUser.getId());
        System.out.println("수정 완료 : ID = " + modifyMessage.getId());

        // 3 - 4. 수정된 데이터 조회
        printStep(4, "수정된 데이터 조회");
        System.out.println(messageService.find(modifyMessage.getId()));

        // 3 - 5. 삭제
        printStep(5, "삭제");
        messageService.delete(modifyMessage.getId());
        System.out.println("삭제 완료 : ID = " + modifyMessage.getId());

        // 3 - 6. 삭제된 데이터 확인
        printStep(6, "삭제된 데이터 확인");
        testException(
                "삭제된 메시지 정보 조회",
                IllegalArgumentException.class,
                () -> messageService.find(modifyMessage.getId())
        );
        System.out.println("잔여 데이터 : " + messageService.findAll().size() + "건");
        messageService.findAll().forEach(System.out::println);

        // 3 - 7. 예외 처리 테스트
        printStep(7, "예외 처리 테스트");

        // 존재하지 않는 메시지 조회
        testException(
                "존재하지 않는 메시지 조회",
                IllegalArgumentException.class,
                () -> messageService.find(UUID.randomUUID())
        );

        // 3 - 8. 도메인 관계 검증 테스트
        printStep(8, "도메인 관계 검증 테스트");

        UUID validUserId = createUser.getId();
        UUID validChannelId = createChannel.getId();
        UUID deletedUserId = modifyUser.getId();
        UUID deletedChannelId = modifyChannel.getId();

        // 존재하지 않는 사용자로 메시지 등록
        testException(
                "존재하지 않는 사용자로 메시지 등록",
                IllegalArgumentException.class,
                () -> messageService.create(
                        "잘못된 사용자 테스트"
                        , validChannelId
                        , UUID.randomUUID()
                )
        );

        // 존재하지 않는 채널로 메시지 등록
        testException(
                "존재하지 않는 채널로 메시지 등록",
                IllegalArgumentException.class,
                () -> messageService.create(
                        "잘못된 채널 테스트"
                        , UUID.randomUUID()
                        , validUserId
                )
        );

        // 삭제된 사용자로 메시지 등록
        testException(
                "삭제된 사용자로 메시지 등록",
                IllegalArgumentException.class,
                () -> messageService.create(
                        "삭제된 사용자 테스트"
                        , validChannelId
                        , deletedUserId
                )
        );

        // 삭제된 채널로 메시지 등록
        testException(
                "삭제된 채널로 메시지 등록",
                IllegalArgumentException.class,
                () -> messageService.create(
                        "삭제된 채널 테스트"
                        , deletedChannelId
                        , validUserId
                )
        );

        printEnd("MESSAGE");
    }

    private static void printTitle(String domain) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("    " + domain + " DOMAIN - CRUD TEST");
        System.out.println("==================================================");
    }

    private static void printStep(int step, String description) {
        System.out.println();
        System.out.printf("[%02d] %s%n", step, description);
        System.out.println("--------------------------------------------------");
    }

    private static void printEnd(String domain) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("    " + domain + " DOMAIN - TEST END");
        System.out.println("==================================================");
        System.out.println();
    }

    private static void testException(
            String description
            , Class<? extends Exception> expectedType
            , Runnable action
    ) {
        System.out.println();
        System.out.println("[TEST] " + description);
        System.out.println("--------------------------------------------------");

        try {
            action.run();

            System.out.println("[FAIL] 예외가 발생하지 않았습니다.");
        } catch (Exception e) {
            boolean passed = expectedType.isInstance(e);

            System.out.println("검증 결과 : " + (passed ? "PASS" : "FAIL"));
            System.out.println("예상 예외 : " + expectedType.getSimpleName());
            System.out.println("발생 예외 : " + e.getClass().getSimpleName());
            System.out.println("예외 내용 : " + e.getMessage());
        }
    }
}
