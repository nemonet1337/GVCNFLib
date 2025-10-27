このmodはゲリラmod NeoForge1.21.1版の前提modです。

必ずセーブ毎にバックアップを取って使用してください。

---

## <導入方法>

1. neoForge 1.21.1を導入する。
2. このjarをmodフォルダに入れる。
3. ゲームを起動してタイトルまで行ければ導入成功です。

---

## <config設定>

### gvclib.cfg

#### 設定方法

- B:○○=true/false
- I:○○=整数値
- D:○○=小数点を含む数値
- S:○○=文字列

#### デバッグモード関連

```cfg
debag_mode {
    # debag_mode [default: false]デバッグモードの可否
    B:cfg_debag=false

    # cfg_debag_gun_mugen [default: false]デバッグモード時、銃のリロードで弾が消費されない
    B:cfg_debag_gun_mugen=false

    # debag_weather_mode [default: false]デバッグモード時、天候固定
    B:cfg_debag_weather=false
}
```

#### エンティティ関連

```cfg
entity {
    # Instant_death_avoidance [default: true]プレイヤ-の体力満タン時一撃死の回避の可否
    B:cfg_Instant_death_avoidance=true

    # bullet_living [range: 0 ~ 8000, default: 80]銃弾の継続時間
    I:cfg_bullet_living=80

    # bullet_smoke [default: false]銃弾の軌跡の煙パーテクルの可否
    B:cfg_bullet_smoke=false

    # mobdismount_insave [default: true]セーブ時のモブの降車の可否
    B:cfg_mobdismount_insave=true

    # turret_lockpoint [default: true]タレット系の位置固定の可否
    B:cfg_turret_lockpoint=true

    # vehicle_death_fire [default: true]搭乗兵器が破壊されたときの炎エフェクトの可否
    B:cfg_vehicle_death_fire=true

    # vehicle_server_client_async [default: true]搭乗者のサーバー/クライアント側の位置調整の可否
    B:cfg_vehicle_server_client_async=true
}
```

#### エンティティのレンダリング関連

```cfg
entityrender {
    # entity_render_range [range: 1.0 ~ 100.0, default: 20.0]エンティティの描画距離
    S:cfg_entity_render_range=20.0
}
```

#### 爆発関連

```cfg
explotion {
    # explotion_breakdirt [default: true]爆発で土ブロック等一部のブロックの破壊不可能の可否
    B:cfg_explotion_breakdirt=true

    # explotion_drop [range: 0.0 ~ 1.0, default: 1.0]爆発時のドロップ率
    S:cfg_explotion_drop=1.0
}
```

#### 銃関連

```cfg
gun {
    # arm [default: false]一人称視点の腕の表示をLMM式の腕に変更の可否
    B:arm_lmm=false
}
```

#### キー関連

```cfg
key {
    # KEY_B [default: B]
    S:cfg_key_b=B

    # KEY_C [default: C]
    S:cfg_key_c=C

    # KEY_F [default: F]
    S:cfg_key_f=F

    # KEY_G [default: G]
    S:cfg_key_g=G

    # KEY_H [default: H]
    S:cfg_key_h=H

    # KEY_K [default: K]
    S:cfg_key_k=K

    # KEY_R [default: R]
    S:cfg_key_r=R

    # KEY_TAB [default: TAB]
    S:cfg_key_tab=TAB

    # KEY_V [default: V]
    S:cfg_key_v=V

    # KEY_X [default: X]
    S:cfg_key_x=X

    # KEY_Z [default: Z]
    S:cfg_key_z=Z
}
```

#### OPTIFINE関連(現在未機能なので削除予定)

```cfg
optifine {
    # optifine [default: false]
    B:cfg_optifine=false

    # optifine [range: 0.0 ~ 20.0, default: 1.6]
    S:cfg_optifiney=1.6

    # optifine [range: 0.0 ~ 20.0, default: 1.54]
    S:cfg_optifineys=1.54
}
```

#### システム関連

```cfg
system {
    # debag_mode [default: true]起動時のマルチコア処理の可否
    B:cfg_multiCoreLoading=true

    # particle_limiter [default: true]パーテクルの上限設定の可否
    B:cfg_particle_limiter=true

    # particle_limiter_kazu [range: 0 ~ 16383, default: 9999]パーテクルの上限量
    I:cfg_particle_limiter_kazu=9999
}
```

---

## <src>

このmodではゲリラmod等で使用されている銃やentityなどを定義しています。  
ソースはsrcフォルダに付属しているのでご自由にご利用ください。

---

## <mqoモデルについて>

objモデルと同じように使用できます。  
作画されるモデルはオブジェクトのほうです。(材質は関係なし)  
だたし、銃系モデルはオブジェクト名をmat1,mat2というようにしてください。  
作成にあたってEMB4氏のものを参考にさせていただきました。  
http://forum.minecraftuser.jp/viewtopic.php?f=13&t=14837&start=2800#p288137

---

## <スペシャルサンクス>

- MG36様 元Mod作者様
- EMB4様　MQOモデルローダー作成
- frou01様　モデル読み込み速度向上

---

## <更新履歴>

```text
10/27 1.0.0 公開
```