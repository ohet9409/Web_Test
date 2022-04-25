package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoEntity {
    private String id;      // �� ������Ʈ�� ���̵�
    private String userId;  // �� ������Ʈ�� ������ ������� ���̵�
    private String title;   // Todo Ÿ��Ʋ(��: ��ϱ�)
    private boolean done;   // true - todo�� �Ϸ��� ���(checkd)
}